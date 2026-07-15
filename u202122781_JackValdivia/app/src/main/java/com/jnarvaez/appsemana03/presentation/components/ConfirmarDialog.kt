package com.jnarvaez.appsemana03.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ConfirmarDialog(titulo: String, item: String, onConfirm: ()-> Unit, onDismiss: ()-> Unit){
    AlertDialog(
        onDismissRequest = onDismiss,
        title  = { Text(titulo) },
        text   = { Text("¿Deseas eliminar a $item?\nEsta acción no se puede deshacer.") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Eliminar", color = MaterialTheme.colorScheme.error)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}