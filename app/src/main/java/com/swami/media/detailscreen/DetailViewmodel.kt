package com.swami.media.detailscreen

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swami.media.data.FeedItemData
import com.swami.media.feed.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailUiState(
    val item: FeedItemData? = null,
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val itemId:String?=""
)
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: FeedRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun setItemId(itemId: Long) {
        _uiState.value = _uiState.value.copy(itemId = itemId.toString())
        loadDetail(itemId)
    }

    private fun loadDetail(itemId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            val item: FeedItemData? = repository.getFeedItemById(itemId)

            if (item != null) {
                _uiState.value = _uiState.value.copy(item = item, isLoading = false)
            } else {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Item with ID $itemId not found."
                )
            }
        }
    }
}

