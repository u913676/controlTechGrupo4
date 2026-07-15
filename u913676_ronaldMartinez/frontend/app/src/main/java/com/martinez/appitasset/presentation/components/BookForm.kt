package com.martinez.appitasset.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BookForm(
    title: String,
    author: String,
    year: String,
    gender: String,
    description: String,
    onTitleChange: (String) -> Unit,
    onAuthorChange: (String) -> Unit,
    onYearChange: (String) -> Unit,
    onGenderChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit
){
    OutlinedTextField(
        value = title,
        onValueChange = onTitleChange,
        label = { Text("Tiitle") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = author,
        onValueChange = onAuthorChange,
        label = { Text("Author") },
        modifier = Modifier.fillMaxWidth()
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        OutlinedTextField(
            value = year,
            onValueChange = onYearChange,
            label = { Text("Year") },
            modifier = Modifier.weight(1f)
        )
        OutlinedTextField(
            value = gender,
            onValueChange = onGenderChange,
            label = { Text("Gender") },
            modifier = Modifier.weight(2f)

        )
    }
    OutlinedTextField(
        value = description,
        onValueChange = onDescriptionChange,
        label = { Text("Description") },
        modifier = Modifier.fillMaxWidth(),
        minLines = 4
    )
}