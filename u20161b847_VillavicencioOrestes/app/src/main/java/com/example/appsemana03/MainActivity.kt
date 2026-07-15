package com.example.appsemana03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.appsemana03.core.navigation.AppNavigation
import com.example.appsemana03.presentation.components.AppScaffold
import com.example.appsemana03.ui.theme.AppSemana03Theme

class MainActivity : ComponentActivity() {
    private val container by lazy {
        (application as LibroApplication).container
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppSemana03Theme {
                val navHostController = rememberNavController()
                AppScaffold(navHostController) { paddingValues ->
                    AppNavigation(navHostController,container, paddingValues)
                }
            }
        }
    }
}
