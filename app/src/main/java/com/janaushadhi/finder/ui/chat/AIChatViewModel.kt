package com.janaushadhi.finder.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janaushadhi.finder.data.local.MedicineInfoProvider
import com.janaushadhi.finder.data.model.ChatMessage
import com.janaushadhi.finder.data.model.ChatUiState
import com.janaushadhi.finder.data.repository.GeminiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AIChatViewModel @Inject constructor(
    private val repository: GeminiRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    fun sendMessage(text: String) {
        if (text.isBlank()) return

        val userMessage = ChatMessage(text = text, isUser = true)
        _uiState.update { it.copy(
            messages = it.messages + userMessage,
            isLoading = true,
            error = null
        ) }

        viewModelScope.launch {
            // Check for predefined responses first
            val localResponse = MedicineInfoProvider.findResponse(text)
            
            val responseText = if (localResponse != null) {
                delay(800) // Small delay for realism
                localResponse
            } else {
                repository.getChatResponse(text, _uiState.value.selectedLanguage)
            }

            val aiMessage = ChatMessage(text = responseText, isUser = false)
            
            _uiState.update { it.copy(
                messages = it.messages + aiMessage,
                isLoading = false
            ) }
        }
    }

    fun setLanguage(language: String) {
        _uiState.update { it.copy(selectedLanguage = language) }
    }

    fun clearChat() {
        _uiState.update { it.copy(messages = emptyList(), error = null) }
    }
}
