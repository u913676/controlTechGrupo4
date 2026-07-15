package com.martinez.appitasset.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martinez.appitasset.domain.model.ITAssetModel
import com.martinez.appitasset.domain.usecase.ITAssetUseCases
import com.martinez.appitasset.presentation.event.EventBus
import com.martinez.appitasset.presentation.event.UiEvent
import com.martinez.appitasset.presentation.screens.list.ListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListViewModel(private val useCases: ITAssetUseCases): ViewModel() {
    private val _uiState = MutableStateFlow(ListUiState())
    val uiState = _uiState.asStateFlow()
    private var items = listOf<ITAssetModel>()

    init {
        loadItems()
    }
    fun loadItems(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                items = useCases.getITAssets()
                _uiState.value = _uiState.value.copy(
                    lisItems = items,
                    isLoading = false,
                    search = "",
                    listItemsFilter = emptyList())
            }catch (e: Exception){
                _uiState.value = _uiState.value.copy(
                    isLoading = false, error = e.message)
                EventBus.send(UiEvent.Error(
                    "Error al listar libros: ${e.message}"))
            }

        }
    }

    fun setDelete(estado: Boolean){
        _uiState.value = _uiState.value.copy(isDelete = estado)
    }

    fun deleteItem(id: String){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                useCases.deleteITAsset(id)
                EventBus.send((UiEvent.Success("Equipo eliminado")))
                _uiState.value = _uiState.value.copy(isLoading = false)
                loadItems()
            }catch (e: Exception){
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
                EventBus.send(UiEvent.Error(
                    "Error al eliminar el equipo: ${e.message}"))
            }
        }
    }

    fun onSearchChange(value: String) {
        _uiState.update {
            it.copy(search = value)
        }
        val filtered = items.filter {
            "${it.serialNumber} ${it.serialNumber}"
                .contains(value, true)
        }
        _uiState.update {
            it.copy(listItemsFilter = filtered)
        }
    }
}