package com.martinez.appitasset.presentation.viewmodel

import com.martinez.appitasset.domain.model.ITAssetModel
import com.martinez.appitasset.domain.usecase.ITAssetUseCases
import com.martinez.appitasset.presentation.event.EventBus
import com.martinez.appitasset.presentation.event.UiEvent
import com.martinez.appitasset.presentation.screens.view.ViewUIState

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.String

class ViewViewModel(private val useCases: ITAssetUseCases): ViewModel() {
    private val _uiState = MutableStateFlow(ViewUIState())
    val uiState = _uiState.asStateFlow()

    fun getItem(id: String){
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val item = useCases.getITAsset(id)
                _uiState.value = _uiState.value.copy(
                    id = item.id,
                    equipmentType = item.equipmentType,
                    brand = item.brand,
                    model = item.model,
                    serialNumber = item.serialNumber,
                    assetTag = item.assetTag,
                    status = item.status,
                    location = item.location,
                    asignedUser = item.asignedUser,
                    purchaseDate = item.purchaseDate,
                    notes = item.notes,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message
                )
                EventBus.send(UiEvent.Error(
                    e.message ?: "Error al cargar equipo"))
            }
        }
    }

    fun onEquipmentTypeChange(valor: String){
        _uiState.value = _uiState.value.copy(equipmentType = valor)
    }

    fun onBrandChange(valor: String){
        _uiState.value = _uiState.value.copy(brand = valor)
    }

    fun onModelChange(valor: String){
        _uiState.value = _uiState.value.copy(model = valor)
    }

    fun onSerialNumberChange(valor: String){
        _uiState.value = _uiState.value.copy(serialNumber = valor)
    }

    fun onAssetTagChange(valor: String){
        _uiState.value = _uiState.value.copy(assetTag = valor)
    }

    fun onStatusChange(valor: String){
        _uiState.value = _uiState.value.copy(status = valor)
    }

    fun onAsignedUserChange(valor: String){
        _uiState.value = _uiState.value.copy(asignedUser = valor)
    }

    fun onPurchaseDateChange(valor: String){
        _uiState.value = _uiState.value.copy(purchaseDate = valor)
    }

    fun onNotesChange(valor: String){
        _uiState.value = _uiState.value.copy(notes = valor)
    }

    fun updateItem(){
        viewModelScope.launch {
            if(!validateForm()){
                return@launch
            }
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val modelData = ITAssetModel(
                    "",
                    _uiState.value.equipmentType,
                    _uiState.value.brand,
                    _uiState.value.model,
                    _uiState.value.serialNumber,
                    _uiState.value.assetTag,
                    _uiState.value.status,
                    _uiState.value.location,
                    _uiState.value.asignedUser,
                    _uiState.value.purchaseDate,
                    _uiState.value.notes
                )
                useCases.updateITAsset(modelData)
                clearForm()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isSuccess = true
                )
                EventBus.send(UiEvent.Success(
                    "Equipo actualizado correctamente"))
            }catch (e: Exception){
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message
                )
            }
        }
    }

    private fun clearForm(){
        _uiState.value = _uiState.value.copy(
            id = "",
            equipmentType = "",
            brand = "",
            model = "",
            serialNumber = "",
            assetTag = "",
            status = "",
            location = "",
            asignedUser = "",
            purchaseDate = "",
            notes = ""
        )
    }

    fun clearSuccess(){
        _uiState.value = _uiState.value.copy(isSuccess = false)
    }

    private suspend fun validateForm(): Boolean{
        when{
            _uiState.value.equipmentType.isBlank() -> {
                EventBus.send(UiEvent.Warning("Enter equipmentType"))
                return false
            }
            _uiState.value.brand.isBlank() -> {
                EventBus.send(UiEvent.Warning("Enter brand"))
                return false
            }
            _uiState.value.model.isBlank() -> {
                EventBus.send(UiEvent.Warning("Enter model"))
                return false
            }
            _uiState.value.serialNumber.isBlank() -> {
                EventBus.send(UiEvent.Warning("Enter serialNumber"))
                return false
            }
            _uiState.value.assetTag.isBlank() -> {
                EventBus.send(UiEvent.Warning("Enter assetTag"))
                return false
            }
        }
        return true
    }
}