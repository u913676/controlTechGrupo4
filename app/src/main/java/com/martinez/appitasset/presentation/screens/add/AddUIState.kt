package com.martinez.appitasset.presentation.screens.add

data class AddUIState (
    val title: String = "",
    val author: String = "",
    val year: String = "",
    val gender: String = "",
    val description: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)


