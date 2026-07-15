package com.martinez.appitasset.presentation.screens.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.martinez.appitasset.di.AppContainer
import com.martinez.appitasset.presentation.components.LoadingScreen
import com.martinez.appitasset.presentation.components.ViewITAssetCard

@Composable
fun ViewScreen(
    container: AppContainer,
    navHostController: NavHostController,
    id: String
) {

    val viewModel = container.viewViewModel
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(id) {
        viewModel.getItem(id)
    }

    if(uiState.isLoading && uiState.id.isEmpty()){
        LoadingScreen()
    }else {
        ViewITAssetCard(uiState)
    }

}

