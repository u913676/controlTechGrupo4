package com.martinez.appitasset.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martinez.appitasset.domain.model.ITAssetModel
import com.martinez.appitasset.domain.usecase.ITAssetUseCases
import com.martinez.appitasset.presentation.event.EventBus
import com.martinez.appitasset.presentation.event.UiEvent
import com.martinez.appitasset.presentation.screens.register.RegisterUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(private val useCases: ITAssetUseCases) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUIState())
    val uiState = _uiState.asStateFlow()

    fun onEquipmentTypeChange(value: String){
        _uiState.value = uiState.value.copy(equipmentType = value)
    }

    fun onBrandChange(value: String){
        _uiState.value = uiState.value.copy(brand = value)
    }

    fun onModelChange(value: String){
        _uiState.value = uiState.value.copy(model = value)
    }

    fun onSerialNumberChange(value: String){
        _uiState.value = uiState.value.copy(serialNumber = value)
    }

    fun onAssetTagChange(value: String){
        _uiState.value = uiState.value.copy(assetTag = value)
    }

    fun onStatusChange(value: String){
        _uiState.value = uiState.value.copy(status = value)
    }

    fun onLocationChange(value: String){
        _uiState.value = uiState.value.copy(location = value)
    }

    fun onAsignedUserChange(value: String){
        _uiState.value = uiState.value.copy(asignedUser = value)
    }

    fun onPurchaseDateChange(value: String){
        _uiState.value = uiState.value.copy(purchaseDate = value)
    }

    fun onNotesChange(value: String){
        _uiState.value = uiState.value.copy(notes = value)
    }

    fun onSaveData(){
        viewModelScope.launch {
            if (!validateForm()){
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

                useCases.addITAsset(modelData)
                clearForm()
                clearSuccess()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isSuccess = true
                )

                EventBus.send(UiEvent.Success("Equipo registrado correctamente"))
            }catch (e: Exception){
                println("onSaveData.e.Exception: ${e.message}")
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message
                )

                EventBus.send(UiEvent.Error(
                    "${e.message}"))

            }

        }
    }

    private fun clearForm(){
        _uiState.value = _uiState.value.copy(
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

    private suspend fun validateForm(): Boolean {
        when {
            _uiState.value.equipmentType.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter equipmentType"))
                return false
            }
            _uiState.value.brand.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter brand"))
                return false
            }
            _uiState.value.model.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter model"))
                return false
            }
            _uiState.value.serialNumber.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter serialNumber"))
                return false
            }
            _uiState.value.assetTag.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter assetTag"))
                return false
            }
            _uiState.value.status.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter status"))
                return false
            }
            _uiState.value.location.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter location"))
                return false
            }
            _uiState.value.asignedUser.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter asignedUser"))
                return false
            }
            _uiState.value.purchaseDate.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter purchaseDate"))
                return false
            }
            _uiState.value.notes.isBlank() -> {
                EventBus.send(UiEvent.Warning(
                    message = "Enter notes"))
                return false
            }
        }

//        EventBus.send(UiEvent.Success(
//            message = "Save data"))
        return true
    }

}