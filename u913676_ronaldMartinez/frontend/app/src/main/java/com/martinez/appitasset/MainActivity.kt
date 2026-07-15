package com.martinez.appitasset

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

import com.martinez.appitasset.presentation.navigation.AppNavigation
import com.martinez.appitasset.presentation.components.AppScaffold
import com.martinez.appitasset.ui.theme.AppTIAssetTheme

class MainActivity : ComponentActivity() {
    private val container by lazy {
        (application as AppLibrary).container
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTIAssetTheme {
                val navController = rememberNavController()
                AppScaffold(navController) { paddingValues ->
                    AppNavigation(
                        navController,
                        container,
                        paddingValues
                    )
                }
            }
        }
    }
}