package com.martinez.appitasset.presentation.screens.list

import com.martinez.appitasset.domain.model.ITAssetModel

data class ListUiState(
    val lisItems : List<ITAssetModel> = emptyList(),
    val isLoading : Boolean = false,
    val error : String? = null,
    val isDelete : Boolean = false,
    val search: String = "",
    val listItemsFilter : List<ITAssetModel> = emptyList()
)