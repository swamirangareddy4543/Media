package com.swami.media.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.swami.media.detailscreen.DetailScreen
import com.swami.media.feed.FeedScreen
import com.swami.media.ui.theme.VisualSphereTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VisualSphereTheme {
                AppNavigation()
            }
        }
    }
}

// Define routes
sealed class Screen(val route: String) {
    object Auth : Screen("auth_screen")
    object Feed : Screen("feed_screen")
    object Detail : Screen("detail_screen/{itemId}") {
        fun createRoute(itemId: Long) = "detail_screen/$itemId"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: LoginViewModel = hiltViewModel()

    // ✅ Automatically skip login if user already logged in
    val startDestination = if (viewModel.isUserLoggedIn()) {
        Screen.Feed.route
    } else {
        Screen.Auth.route
    }

    NavHost(navController = navController, startDestination = startDestination) {

        // Authentication Screen (Login/Register)
        composable(Screen.Auth.route) {
            LoginScreen(
                onAuthSuccess = { username ->
                    navController.navigate(Screen.Feed.route) {
                        popUpTo(Screen.Auth.route) { inclusive = true }
                    }
                }
            )
        }

        // Main Feed Screen
        composable(Screen.Feed.route) {
            FeedScreen(navController = navController)
        }

        // Detail Screen
        composable(Screen.Detail.route) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")?.toLongOrNull()
            itemId?.let {
                DetailScreen(itemId = it, navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VisualSphereTheme {
        LoginScreen(onAuthSuccess = {})
    }
}