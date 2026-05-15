package com.janaushadhi.finder.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicines")
data class MedicineEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val brandName: String,
    val genericName: String,
    val saltComposition: String,
    val brandPrice: Double,
    val genericPrice: Double,
    val category: String = "General",
    val manufacturer: String = "Jan Aushadhi"
) {
    val savingsPercent: Int
        get() = if (brandPrice > 0) {
            ((brandPrice - genericPrice) / brandPrice * 100).toInt()
        } else 0
}
