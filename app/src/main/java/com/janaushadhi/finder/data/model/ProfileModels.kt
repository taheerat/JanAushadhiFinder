package com.janaushadhi.finder.data.model

data class UserProfile(
    val name: String,
    val phone: String,
    val email: String,
    val profilePicUrl: String? = null
)

data class SearchHistoryItem(
    val id: String,
    val query: String,
    val type: HistoryType,
    val date: String
)

enum class HistoryType {
    MEDICINE, AI_QUERY, STORE
}

data class PrescriptionItem(
    val id: String,
    val fileName: String,
    val date: String,
    val doctorName: String,
    val medicineCount: Int
)

data class SettingsState(
    val isDarkMode: Boolean = false,
    val notificationsEnabled: Boolean = true,
    val medicineRemindersEnabled: Boolean = true,
    val language: String = "English"
)

data class ProfileUiState(
    val user: UserProfile? = null,
    val history: List<SearchHistoryItem> = emptyList(),
    val prescriptions: List<PrescriptionItem> = emptyList(),
    val settings: SettingsState = SettingsState(),
    val showLogoutDialog: Boolean = false,
    val showEditDialog: Boolean = false,
    val showHelpDialog: Boolean = false,
    val isLoading: Boolean = false
)
