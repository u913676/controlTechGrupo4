package com.jnarvaez.appsemana03.presentation.event

sealed interface UiEvent {
    data class Success(val mensaje: String): UiEvent
    data class Error(val mensaje: String): UiEvent
    data class Warning(val mensaje: String): UiEvent
}