package com.martinez.appitasset.presentation.screens.register

data class RegisterUIState (
    val equipmentType: String = "",
    val brand: String = "",
    val model: String = "",
    val serialNumber: String = "",
    val assetTag: String = "",
    val status: String = "",
    val location: String = "",
    val asignedUser: String = "",
    val purchaseDate: String = "",
    val notes: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)