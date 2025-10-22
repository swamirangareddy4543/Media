package com.swami.media.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swami.media.data.FeedItemData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FeedUiState(
    val items: List<FeedItemData> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)
@HiltViewModel
class FeedViewModel @Inject constructor(
    private val repository: FeedRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FeedUiState())
    val uiState: StateFlow<FeedUiState> = _uiState.asStateFlow()

    init {
        loadFeed()
    }

    fun loadFeed() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            val result = repository.getAllFeedItems()

            result.fold(
                onSuccess = { items ->
                    _uiState.value = _uiState.value.copy(items = items, isLoading = false)
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(
                        items = emptyList(),
                        isLoading = false,
                        errorMessage = error.message ?: "An unknown error occurred."
                    )
                }
            )
        }
    }
}