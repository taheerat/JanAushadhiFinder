package com.janaushadhi.finder.data.model

data class Store(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val phone: String,
    val isOpen: Boolean = true,
    val distance: String = "1.2 km"
)
