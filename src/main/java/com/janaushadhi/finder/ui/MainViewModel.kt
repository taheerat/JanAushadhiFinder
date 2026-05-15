package com.janaushadhi.finder.ui

import androidx.lifecycle.ViewModel
import com.janaushadhi.finder.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    val isDarkMode: StateFlow<Boolean> = settingsRepository.isDarkMode
}
