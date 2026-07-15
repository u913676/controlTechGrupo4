package com.martinez.appitasset.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martinez.appitasset.presentation.event.EventBus
import com.martinez.appitasset.presentation.event.UiEvent
import com.martinez.appitasset.presentation.screens.search.SearchUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(SearchUIState())
    val uiState = _uiState.asStateFlow()

    fun onNameChange(value: String){
        _uiState.value = uiState.value.copy(name = value)
    }

    fun onDocumentNumberChange(value: String){
        _uiState.value = uiState.value.copy(documentNumber = value)
    }

    fun onAgeChange(value: String){
        _uiState.value = uiState.value.copy(age = value)
    }

    fun onCycleChange(value: String){
        _uiState.value = uiState.value.copy(cycle = value)
    }

    fun onFacultyChange(value: String){
        _uiState.value = uiState.value.copy(faculty = value)
    }

    fun onCareerChange(value: String){
        _uiState.value = uiState.value.copy(career = value)
    }

    fun onAddressChange(value: String){
        _uiState.value = uiState.value.copy(address = value)
    }

    fun onEmailChange(value: String){
        _uiState.value = uiState.value.copy(email = value)
    }

    fun onLinkedinChange(value: String){
        _uiState.value = uiState.value.copy(linkedin = value)
    }

    fun searchBook(){
        viewModelScope.launch {
            if (!validateForm()){
                return@launch
            }
        }
    }

    private suspend fun validateForm(): Boolean {
        when {
            _uiState.value.name.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter name"))
                return false
            }
            _uiState.value.documentNumber.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter document number"))
                return false
            }
            _uiState.value.age.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter age"))
                return false
            }
            _uiState.value.cycle.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter cycle"))
                return false
            }
            _uiState.value.faculty.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter faculty"))
                return false
            }
            _uiState.value.career.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter career"))
                return false
            }
            _uiState.value.address.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter address"))
                return false
            }
            _uiState.value.email.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter email"))
                return false
            }
            _uiState.value.linkedin.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter linkedin"))
                return false
            }
        }

        EventBus.send(UiEvent.Success(
            message = "Search data"))
        return true
    }

}