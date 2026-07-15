package com.martinez.appitasset.presentation.screens.search

data class SearchUIState (
    val name: String = "",
    val documentNumber: String = "",
    val age: String = "",
    val cycle: String = "",
    val faculty: String = "",
    val career: String = "",
    val address: String = "",
    val email: String = "",
    val linkedin: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)


