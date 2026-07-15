package com.example.appsemana03.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appsemana03.presentation.event.EventBus
import com.example.appsemana03.presentation.event.UiEvent
import com.example.appsemana03.presentation.screens.agregar.AgregarUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AgregarViewModel () :  ViewModel () {
    private val _uiState = MutableStateFlow(value = AgregarUiState())
    val uiState = _uiState.asStateFlow()

    fun onTituloChange(valor: String){
         _uiState.value = uiState.value.copy(titulo = valor)
    }

    fun onAutorChange(valor: String){
        _uiState.value = uiState.value.copy(autor = valor)
    }

    fun onAnioChange(valor: String){
        _uiState.value = uiState.value.copy(anio = valor)
    }

    fun onGeneroChange(valor: String){
        _uiState.value = uiState.value.copy(genero = valor)
    }

    fun onDescripcionChange(valor: String){
        _uiState.value = uiState.value.copy(descripcion = valor)
    }

    fun guardarLibro(){
         viewModelScope.launch {
             if(!validarFormulario()){
                 return@launch
             }
         }
    }

    private suspend fun validarFormulario(): Boolean{
        when{
            _uiState.value.titulo.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese el titulo"))
                return false
            }
            _uiState.value.autor.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese el autor"))
                return false
            }
            _uiState.value.genero.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese el genero"))
                return false
            }
            _uiState.value.anio.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese el año"))
                return false
            }
            _uiState.value.descripcion.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese el descripcion"))
                return false
            }
        }
        return true
    }
}