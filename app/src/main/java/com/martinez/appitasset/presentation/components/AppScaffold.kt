package com.martinez.appitasset.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import com.martinez.appitasset.presentation.event.EventBus
import com.martinez.appitasset.presentation.event.UiEvent
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds


@Composable
fun AppScaffold(
    navHostController: NavHostController,
    content:@Composable (PaddingValues) -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        EventBus.events.collect { event ->
            when(event) {
                is UiEvent.Success ->  {
                    snackbarHostState.showSnackbar(
                        SnackbarCustom(
                            message = event.message,
                            status = SnackbarStatus.SUCCESS
                        )
                    )

                }
                is UiEvent.Error ->  {
                    snackbarHostState.showSnackbar(
                        SnackbarCustom(
                            message = event.message,
                            status = SnackbarStatus.ERROR
                        )
                    )

                }
                is UiEvent.Warning ->  {
                    snackbarHostState.showSnackbar(
                        SnackbarCustom(
                            message = event.message,
                            status = SnackbarStatus.WARNING,
                            duration = SnackbarDuration.Short // duración base
                        )
                    )
                    // Espera menos tiempo y lo cierra


                }
            }
        }

        //snackbarHostState.currentSnackbarData?.dismiss()
    }

    Scaffold(
        topBar = {
            AppTopBar(navHostController)
        },
        bottomBar = {
            AppButtonBar(navHostController)
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState){ data ->
                Customisation(data)
            }
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}

/**
 * Muestra un snackbar personalizado con ícono y colores.
 */
@Composable
private fun Customisation(data: SnackbarData){
    val custom = data.visuals as? SnackbarCustom
    if (custom != null) {
        val status = custom.status
        Snackbar(
            containerColor = status.backgroundColor,
            contentColor = status.textColor,
            modifier = Modifier.padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    status.icon,
                    contentDescription = "",
                    tint = status.textColor
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(custom.message)
            }
        }
    } else {
        Snackbar(snackbarData = data)
    }
}