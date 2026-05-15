package com.janaushadhi.finder.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janaushadhi.finder.data.local.session.SessionManager
import com.janaushadhi.finder.data.model.*
import com.janaushadhi.finder.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadProfileData()
        observeSettings()
    }

    private fun observeSettings() {
        viewModelScope.launch {
            settingsRepository.isDarkMode.collect { isDark ->
                _uiState.update { it.copy(settings = it.settings.copy(isDarkMode = isDark)) }
            }
        }
    }

    private fun loadProfileData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            val user = UserProfile(
                name = sessionManager.getUserName(),
                phone = sessionManager.getUserPhone(),
                email = "user@janaushadhi.com" // Default or could be added to session
            )

            val mockHistory = listOf(
                SearchHistoryItem("1", "Paracetamol", HistoryType.MEDICINE, "2 hours ago"),
                SearchHistoryItem("2", "Side effects of Amoxicillin", HistoryType.AI_QUERY, "Yesterday"),
                SearchHistoryItem("3", "Apollo Pharmacy, Delhi", HistoryType.STORE, "2 days ago"),
                SearchHistoryItem("4", "Ibuprofen", HistoryType.MEDICINE, "3 days ago")
            )

            val mockPrescriptions = listOf(
                PrescriptionItem("p1", "Prescription_May_2024.pdf", "15 May 2024", "Dr. Sharma", 4),
                PrescriptionItem("p2", "Clinic_Report_June.pdf", "02 June 2024", "Dr. Verma", 2)
            )

            _uiState.update {
                it.copy(
                    user = user,
                    history = mockHistory,
                    prescriptions = mockPrescriptions,
                    isLoading = false
                )
            }
        }
    }

    fun toggleDarkMode(enabled: Boolean) {
        settingsRepository.setDarkMode(enabled)
    }

    fun toggleNotifications(enabled: Boolean) {
        _uiState.update { it.copy(settings = it.settings.copy(notificationsEnabled = enabled)) }
    }

    fun toggleReminders(enabled: Boolean) {
        _uiState.update { it.copy(settings = it.settings.copy(medicineRemindersEnabled = enabled)) }
    }

    fun deletePrescription(id: String) {
        _uiState.update { state ->
            state.copy(prescriptions = state.prescriptions.filter { it.id != id })
        }
    }

    fun setShowLogoutDialog(show: Boolean) {
        _uiState.update { it.copy(showLogoutDialog = show) }
    }

    fun setShowEditDialog(show: Boolean) {
        _uiState.update { it.copy(showEditDialog = show) }
    }

    fun setShowHelpDialog(show: Boolean) {
        _uiState.update { it.copy(showHelpDialog = show) }
    }

    fun updateProfile(name: String, phone: String) {
        sessionManager.saveUser(name, phone)
        _uiState.update { state ->
            state.copy(
                user = state.user?.copy(name = name, phone = phone),
                showEditDialog = false
            )
        }
    }

    fun logout(onLogoutSuccess: () -> Unit) {
        sessionManager.logout()
        _uiState.update { it.copy(showLogoutDialog = false) }
        onLogoutSuccess()
    }
}
