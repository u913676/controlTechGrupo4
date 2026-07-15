package com.sanders.app_controltech.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import com.sanders.app_controltech.model.Equipo
import com.sanders.app_controltech.ui.components.EquipoCard
import com.sanders.app_controltech.ui.equipos.EquiposViewModel
import com.sanders.app_controltech.ui.theme.ControlTechBackground
import com.sanders.app_controltech.ui.theme.ControlTechBlue
import com.sanders.app_controltech.ui.theme.ControlTechBorder
import com.sanders.app_controltech.ui.theme.ControlTechCyan
import com.sanders.app_controltech.ui.theme.ControlTechSurface
import com.sanders.app_controltech.ui.theme.ControlTechTextSecondary

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun EquiposScreen(
    viewModel: EquiposViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = ControlTechBackground,

        topBar = {
            ControlTechTopBar()
        },

        bottomBar = {
            ControlTechBottomBar()
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // En una etapa posterior abrirá la pantalla HU-05.
                },
                containerColor = ControlTechBlue,
                contentColor = ControlTechSurface,
                shape = CircleShape
            ) {
                Text(
                    text = "+",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            BarraBusqueda(
                textoBusqueda = uiState.textoBusqueda,
                onTextoChange = viewModel::onTextoBusquedaChange
            )

            CategoriasFiltro(
                categorias = uiState.categorias,
                categoriaSeleccionada = uiState.categoriaSeleccionada,
                onCategoriaSeleccionada = viewModel::onCategoriaChange
            )

            Text(
                text = "${uiState.equiposFiltrados.size} equipos encontrados",
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 9.dp
                ),
                fontSize = 12.sp,
                color = ControlTechTextSecondary
            )

            when {

                uiState.cargando -> {
                    CargandoEquipos(
                        modifier = Modifier.weight(1f)
                    )
                }

                uiState.mensajeError != null -> {
                    ErrorCargaEquipos(
                        mensaje = uiState.mensajeError ?: "Error desconocido",
                        onReintentar = {
                            viewModel.cargarEquipos()
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                uiState.equiposFiltrados.isEmpty() -> {
                    SinResultados(
                        modifier = Modifier.weight(1f)
                    )
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(
                            start = 14.dp,
                            end = 14.dp,
                            top = 3.dp,
                            bottom = 90.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(
                            items = uiState.equiposFiltrados,
                            key = { it.id }
                        ) { equipo ->

                            EquipoCard(
                                equipo = equipo
                            )

                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ControlTechTopBar() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        color = ControlTechSurface,
        shadowElevation = 3.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 17.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "☰",
                fontSize = 20.sp,
                color = ControlTechBlue
            )

            Text(
                text = "ControlTech",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = ControlTechBlue
            )

            Box(
                modifier = Modifier
                    .size(37.dp)
                    .clip(CircleShape)
                    .background(ControlTechCyan),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "AJ",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = ControlTechSurface
                )
            }
        }
    }
}

@Composable
private fun BarraBusqueda(
    textoBusqueda: String,
    onTextoChange: (String) -> Unit
) {
    OutlinedTextField(
        value = textoBusqueda,
        onValueChange = onTextoChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 14.dp,
                end = 14.dp,
                top = 14.dp
            ),
        placeholder = {
            Text(
                text = "Buscar por nombre, modelo o código...",
                fontSize = 13.sp
            )
        },
        leadingIcon = {
            Text(
                text = "⌕",
                fontSize = 23.sp,
                color = ControlTechTextSecondary
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(13.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = ControlTechCyan,
            unfocusedBorderColor = ControlTechBorder,
            focusedContainerColor = ControlTechSurface,
            unfocusedContainerColor = ControlTechSurface
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoriasFiltro(
    categorias: List<String>,
    categoriaSeleccionada: String,
    onCategoriaSeleccionada: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        contentPadding = PaddingValues(horizontal = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categorias) { categoria ->

            val seleccionada =
                categoria == categoriaSeleccionada

            FilterChip(
                selected = seleccionada,
                onClick = {
                    onCategoriaSeleccionada(categoria)
                },
                label = {
                    Text(
                        text = categoria,
                        fontSize = 11.sp,
                        fontWeight = if (seleccionada) {
                            FontWeight.Bold
                        } else {
                            FontWeight.Normal
                        }
                    )
                },
                shape = RoundedCornerShape(18.dp),
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = ControlTechBlue,
                    selectedLabelColor = ControlTechSurface,
                    containerColor = ControlTechSurface,
                    labelColor = ControlTechTextSecondary
                ),
                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = seleccionada,
                    borderColor = ControlTechBorder,
                    selectedBorderColor = ControlTechBlue
                )
            )
        }
    }
}

@Composable
private fun SinResultados(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "▣",
                fontSize = 48.sp,
                color = ControlTechBorder
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "No se encontraron equipos",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Prueba con otro nombre, código o categoría",
                fontSize = 12.sp,
                color = ControlTechTextSecondary
            )
        }
    }
}

@Composable
private fun ControlTechBottomBar() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        color = ControlTechSurface,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomBarItem(
                simbolo = "⌂",
                texto = "Inicio",
                seleccionado = false
            )

            BottomBarItem(
                simbolo = "▣",
                texto = "Equipos",
                seleccionado = true
            )

            BottomBarItem(
                simbolo = "+",
                texto = "Registrar",
                seleccionado = false
            )

            BottomBarItem(
                simbolo = "↶",
                texto = "Historial",
                seleccionado = false
            )

            BottomBarItem(
                simbolo = "♙",
                texto = "Perfil",
                seleccionado = false
            )
        }
    }
}

@Composable
private fun BottomBarItem(
    simbolo: String,
    texto: String,
    seleccionado: Boolean
) {
    val color = if (seleccionado) {
        ControlTechBlue
    } else {
        ControlTechTextSecondary
    }

    val background = if (seleccionado) {
        ControlTechCyan
    } else {
        ControlTechSurface
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(background)
                .padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = simbolo,
                fontSize = 18.sp,
                color = color,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = texto,
            fontSize = 9.sp,
            color = color,
            fontWeight = if (seleccionado) {
                FontWeight.Bold
            } else {
                FontWeight.Normal
            }
        )
    }
}

@Composable
private fun CargandoEquipos(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        CircularProgressIndicator(
            color = ControlTechBlue
        )

    }
}

@Composable
private fun ErrorCargaEquipos(
    mensaje: String,
    onReintentar: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "No se pudieron cargar los equipos",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = mensaje,
            fontSize = 12.sp,
            color = ControlTechTextSecondary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onReintentar,
            colors = ButtonDefaults.buttonColors(
                containerColor = ControlTechBlue
            )
        ) {

            Text("Reintentar")

        }

    }

}
