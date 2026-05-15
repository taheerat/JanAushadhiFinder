package com.janaushadhi.finder.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janaushadhi.finder.data.local.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthUiState(
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun onLogin(name: String, phone: String, onNavigateToHome: () -> Unit) {
        if (name.isBlank()) {
            _uiState.update { it.copy(error = "Please enter your full name") }
            return
        }
        if (phone.length != 10 || !phone.all { it.isDigit() }) {
            _uiState.update { it.copy(error = "Please enter a valid 10-digit phone number") }
            return
        }

        _uiState.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            // Simulate network/processing delay for a modern feel
            delay(1500) 
            sessionManager.saveUser(name, phone)
            _uiState.update { it.copy(isLoading = false) }
            onNavigateToHome()
        }
    }

    fun checkLoginStatus(onStatusChecked: (Boolean) -> Unit) {
        viewModelScope.launch {
            delay(2000) // Minimum splash time for branding
            onStatusChecked(sessionManager.isLoggedIn())
        }
    }
}
