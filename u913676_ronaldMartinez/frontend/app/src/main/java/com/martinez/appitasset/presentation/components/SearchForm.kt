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
fun SearchForm(
    name: String,
    documentNumber: String,
    age: String,
    cycle: String,
    faculty: String,
    career: String,
    address: String,
    email: String,
    linkedin: String,
    onNameChange: (String) -> Unit,
    onDocumentNumberChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    onCycleChange: (String) -> Unit,
    onFacultyChange: (String) -> Unit,
    onCareerChange: (String) -> Unit,
    onAddressChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onLinkedinChange: (String) -> Unit
){
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("Name") },
        modifier = Modifier.fillMaxWidth()
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        OutlinedTextField(
            value = documentNumber,
            onValueChange = onDocumentNumberChange,
            label = { Text("DNI") },
            modifier = Modifier.weight(1f)

        )
        OutlinedTextField(
            value = age,
            onValueChange = onAgeChange,
            label = { Text("Age") },
            modifier = Modifier.weight(1f)
        )
        OutlinedTextField(
            value = cycle,
            onValueChange = onCycleChange,
            label = { Text("Cycle") },
            modifier = Modifier.weight(1f)
        )
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        OutlinedTextField(
            value = faculty,
            onValueChange = onFacultyChange,
            label = { Text("Faculty") },
            modifier = Modifier.weight(1f)

        )
        OutlinedTextField(
            value = career,
            onValueChange = onCareerChange,
            label = { Text("Career") },
            modifier = Modifier.weight(1f)
        )
    }

    OutlinedTextField(
        value = address,
        onValueChange = onAddressChange,
        label = { Text("Address") },
        modifier = Modifier.fillMaxWidth()
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            modifier = Modifier.weight(1f)

        )
        OutlinedTextField(
            value = linkedin,
            onValueChange = onLinkedinChange,
            label = { Text("Linkedin") },
            modifier = Modifier.weight(1f)
        )
    }

}