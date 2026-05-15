package com.janaushadhi.finder.domain.model

data class Medicine(
    val id: Long,
    val brandName: String,
    val genericName: String,
    val saltComposition: String,
    val brandPrice: Double,
    val genericPrice: Double,
    val category: String,
    val manufacturer: String
) {
    val savingsAmount: Double get() = brandPrice - genericPrice
    val savingsPercent: Int
        get() = if (brandPrice > 0) {
            ((brandPrice - genericPrice) / brandPrice * 100).toInt()
        } else 0
}
