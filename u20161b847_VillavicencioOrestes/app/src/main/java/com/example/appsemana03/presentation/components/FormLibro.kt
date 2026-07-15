package com.example.appsemana03.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun FormLibro(
    titulo: String, autor: String, anio: String, genero: String, descripcion: String,
    onTituloChange:(String)-> Unit, onAutorChange:(String)-> Unit, onAnioChange:(String)-> Unit,
    onGeneroChange:(String)-> Unit, onDescripcionChange:(String)-> Unit,
){
    OutlinedTextField(
        value = titulo,
        onValueChange = onTituloChange,
        label = { Text("Titulo") },
        modifier = Modifier.fillMaxWidth()
    )

    OutlinedTextField(
        value = autor,
        onValueChange = onAutorChange,
        label = { Text("Autor") },
        modifier = Modifier.fillMaxWidth()
    )
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        OutlinedTextField(
            value = anio,
            onValueChange = onAnioChange,
            label = { Text("Año") },
            modifier = Modifier.weight(1f)
        )
        OutlinedTextField(
            value = genero,
            onValueChange = onGeneroChange,
            label = { Text("Genero") },
            modifier = Modifier.weight(2f)
        )

    }
    OutlinedTextField(
        value = descripcion,
        onValueChange = onDescripcionChange,
        label = { Text("Description") },
        modifier = Modifier.fillMaxWidth(),
        minLines = 4
    )
}