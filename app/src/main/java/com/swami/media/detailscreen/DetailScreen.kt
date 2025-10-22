package com.swami.media.detailscreen

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.view.View
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.swami.media.data.ExoPlayerView
import com.swami.media.data.FeedItemData
import com.swami.media.data.MediaItem
import com.swami.media.data.MediaType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import androidx.core.graphics.createBitmap
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    itemId: Long,
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val view = LocalView.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.setItemId(itemId)
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(uiState.item?.title ?: "---") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    uiState.item?.let { item ->
                        IconButton(onClick = {
                            shareScreenshotToWhatsApp(
                                context = context,
                                view = view,
                                snackbarHostState = snackbarHostState,
                                scope = scope
                            )
                        }) {
                            Icon(Icons.Default.Share, contentDescription = "Share via WhatsApp")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                uiState.errorMessage != null -> {
                    Text(
                        text = "Error: ${uiState.errorMessage}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                uiState.item != null -> {
                    DetailContent(item = uiState.item!!, scrollState = scrollState)
                }
            }
        }
    }
}

@Composable
private fun DetailContent(item: FeedItemData, scrollState: ScrollState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // --- Show Video or Image Gallery ---
        MediaSection(mediaItems = item.mediaItems)

        Spacer(modifier = Modifier.height(16.dp))

        // --- Title & Description ---
        Text(
            text = item.title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = item.fullDescription,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Composable
fun MediaSection(mediaItems: List<MediaItem>) {
    val context = LocalContext.current
    val videos = mediaItems.filter { it.type == MediaType.VIDEO }
    val images = mediaItems.filter { it.type == MediaType.IMAGE }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {


        if (videos.isNotEmpty()) {
            videos.forEach { video ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16 / 9f)
                        .padding(bottom = 8.dp)
                ) {
                    ExoPlayerView(context = context, videoUrl = video.url)
                }
            }
        }
        if (images.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 1000.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(images.size) { image ->
                    AsyncImage(
                        ImageRequest.Builder(LocalContext.current)
                            .data(images[image].url)
                            .allowHardware(false)
                            .build(),
                        contentDescription = "Gallery Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No videos or images available",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

fun shareScreenshotToWhatsApp(
    context: Context,
    view: View,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope
) {
    val bitmap = captureViewBitmap(view)
    val uri = saveBitmapToCache(context, bitmap)

    if (uri != null) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "image/*"
            setPackage("com.whatsapp")
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        try {
            context.startActivity(shareIntent)
        } catch (ex: ActivityNotFoundException) {
            scope.launch {
                snackbarHostState.showSnackbar("WhatsApp is not installed on this device")
            }
        }
    } else {
        scope.launch {
            snackbarHostState.showSnackbar("Failed to capture screenshot")
        }
    }
}

fun saveBitmapToCache(context: Context, bitmap: Bitmap): Uri? {
    return try {
        val cachePath = File(context.cacheDir, "images")
        cachePath.mkdirs()
        val file = File(cachePath, "screenshot.png")
        val stream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        stream.flush()
        stream.close()

        FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

fun captureViewBitmap(view: View): Bitmap {
    val bitmap = createBitmap(view.width, view.height)
    val canvas = Canvas(bitmap)
    view.draw(canvas)
    return bitmap
}
