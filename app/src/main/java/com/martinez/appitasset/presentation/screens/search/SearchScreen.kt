package com.martinez.appitasset.presentation.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import com.martinez.appitasset.di.AppContainer
import com.martinez.appitasset.presentation.components.SearchForm

@Composable
fun SearchScreen(container: AppContainer) {

    val viewModel = container.searchViewModel
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        SearchForm(
            name = uiState.name,
            documentNumber = uiState.documentNumber,
            age = uiState.age,
            cycle = uiState.cycle,
            faculty = uiState.faculty,
            career = uiState.career,
            address = uiState.address,
            email = uiState.email,
            linkedin = uiState.linkedin,
            viewModel::onNameChange,
            viewModel::onDocumentNumberChange,
            viewModel::onAgeChange,
            viewModel::onCycleChange,
            viewModel::onFacultyChange,
            viewModel::onCareerChange,
            viewModel::onAddressChange,
            viewModel::onEmailChange,
            viewModel::onLinkedinChange
        )
        Spacer(Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF000000),
                contentColor = Color(0xFFFFFFFFF),
            ),
            onClick = {
                viewModel.searchBook()
            }
        ) {
            Icon(Icons.Default.Save, contentDescription = "")
            Spacer(Modifier.width(8.dp))
            Text("Save Student", fontWeight = FontWeight.Bold)
        }
    }
}
