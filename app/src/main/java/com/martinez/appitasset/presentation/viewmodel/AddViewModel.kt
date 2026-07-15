package com.martinez.appitasset.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martinez.appitasset.presentation.event.EventBus
import com.martinez.appitasset.presentation.event.UiEvent
import com.martinez.appitasset.presentation.screens.add.AddUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(AddUIState())
    val uiState = _uiState.asStateFlow()

    fun onTitleChange(value: String){
        _uiState.value = uiState.value.copy(title = value)
    }

    fun onAuthorChange(value: String){
        _uiState.value = uiState.value.copy(author = value)
    }

    fun onYearChange(value: String){
        _uiState.value = uiState.value.copy(year = value)
    }

    fun onGenderChange(value: String){
        _uiState.value = uiState.value.copy(gender = value)
    }

    fun onDescriptionChange(value: String){
        _uiState.value = uiState.value.copy(description = value)
    }

    fun saveBook(){
        viewModelScope.launch {
            if (!validateForm()){
                return@launch
            }
        }
    }

    private suspend fun validateForm(): Boolean {
        when {
            _uiState.value.title.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter title"))
                return false
            }
            _uiState.value.author.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter author"))
                return false
            }
            _uiState.value.year.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter year"))
                return false
            }
            _uiState.value.gender.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter gender"))
                return false
            }
            _uiState.value.description.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter gender"))
                return false
            }

        }

//        EventBus.send(UiEvent.Success(
//            message = "Save data"))

        return true

    }

}