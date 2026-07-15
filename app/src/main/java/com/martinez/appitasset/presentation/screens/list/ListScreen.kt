package com.martinez.appitasset.presentation.screens.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

import com.martinez.appitasset.di.AppContainer
import com.martinez.appitasset.domain.model.ITAssetModel
import com.martinez.appitasset.presentation.components.ConfirmDialog
import com.martinez.appitasset.presentation.components.EmptyScreen
import com.martinez.appitasset.presentation.components.LoadingScreen
import com.martinez.appitasset.presentation.navigation.NavRoutes

@Composable
fun ListScreen(container: AppContainer, navHostController: NavHostController) {

    val viewModel = container.listViewModel
    val uiState by viewModel.uiState.collectAsState()
    var selectedItem by remember { mutableStateOf<ITAssetModel?>(null) }

    if(uiState.isLoading){
        LoadingScreen()
    }else{
        if(uiState.lisItems.isEmpty()){
            EmptyScreen("No data available")
        }else{
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(uiState.lisItems) { item ->

                    ListItem(
                        model = item,
                        onEdit = {
                            navHostController.navigate(
                            "${NavRoutes.VIEW}/${item.id}")  },
                        onDelete = {
                            selectedItem = item
                            viewModel.setDelete(true)
                        }
                    )

                }
            }
        }
    }

    if(uiState.isDelete){
        ConfirmDialog(
            title = "Delete ITAsset",
            item = selectedItem?.serialNumber ?: "",
            onConfirm = {
                viewModel.deleteItem(selectedItem!!.id)
                viewModel.setDelete(false)
            },
            onDismiss = { viewModel.setDelete(false) }
        )
    }

}
