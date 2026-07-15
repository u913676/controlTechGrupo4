package com.jnarvaez.appsemana03.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jnarvaez.appsemana03.presentation.navigation.NavRutas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(navHostController: NavHostController) {
    val navBackStackEntry = navHostController.currentBackStackEntryAsState()
    val ruta = navBackStackEntry.value?.destination?.route
    val titulo = NavRutas.getTitulo(ruta)
    val mostrarBoton = ruta != NavRutas.EQUIPOS

    CenterAlignedTopAppBar(
        title = {
            Text(
                titulo,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFFFFF)
            )
        },
        navigationIcon = {
            if (mostrarBoton) {
                IconButton(
                    onClick = { navHostController.popBackStack() },
                    colors = IconButtonDefaults.iconButtonColors(contentColor = Color(0xFFFFFFFF))
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "")
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF1A237E)
        )
    )
}
