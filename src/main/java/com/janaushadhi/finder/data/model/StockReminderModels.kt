package com.janaushadhi.finder.data.model

import java.util.UUID

data class StockRequest(
    val id: String = UUID.randomUUID().toString(),
    val medicineName: String,
    val storeName: String,
    val status: StockStatus = StockStatus.PENDING,
    val timestamp: Long = System.currentTimeMillis()
)

enum class StockStatus {
    PENDING, AVAILABLE, OUT_OF_STOCK
}

data class MedicineReminder(
    val id: String = UUID.randomUUID().toString(),
    val medicineName: String,
    val refillDate: String,
    val quantity: String,
    val isActive: Boolean = true
)

data class StockReminderUiState(
    val stockRequests: List<StockRequest> = emptyList(),
    val reminders: List<MedicineReminder> = emptyList(),
    val isSubmitting: Boolean = false,
    val successMessage: String? = null
)
