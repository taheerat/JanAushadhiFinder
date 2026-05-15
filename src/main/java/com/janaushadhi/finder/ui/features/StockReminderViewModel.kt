package com.janaushadhi.finder.ui.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janaushadhi.finder.data.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockReminderViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(StockReminderUiState())
    val uiState: StateFlow<StockReminderUiState> = _uiState.asStateFlow()

    init {
        // Load initial mock data
        _uiState.update { it.copy(
            reminders = listOf(
                MedicineReminder(medicineName = "Amlodipine 5mg", refillDate = "25 Oct", quantity = "30 Tabs"),
                MedicineReminder(medicineName = "Metformin 500mg", refillDate = "02 Nov", quantity = "60 Tabs")
            )
        ) }
    }

    fun requestStock(medicineName: String, storeName: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isSubmitting = true) }
            delay(1500) // Simulate network delay
            
            val newRequest = StockRequest(medicineName = medicineName, storeName = storeName)
            _uiState.update { it.copy(
                stockRequests = listOf(newRequest) + it.stockRequests,
                isSubmitting = false,
                successMessage = "Request sent to $storeName"
            ) }
            delay(2000)
            _uiState.update { it.copy(successMessage = null) }
        }
    }

    fun addReminder(medicineName: String, date: String, quantity: String) {
        val newReminder = MedicineReminder(medicineName = medicineName, refillDate = date, quantity = quantity)
        _uiState.update { it.copy(reminders = it.reminders + newReminder) }
    }

    fun deleteReminder(id: String) {
        _uiState.update { it.copy(reminders = it.reminders.filter { r -> r.id != id }) }
    }
}
