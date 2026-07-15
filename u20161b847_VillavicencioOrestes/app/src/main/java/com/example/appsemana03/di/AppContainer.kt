package com.example.appsemana03.di

import com.example.appsemana03.presentation.viewmodel.AgregarViewModel
import com.example.appsemana03.presentation.viewmodel.AsignarViewModel
import com.example.appsemana03.presentation.viewmodel.BuscarViewModel
import com.example.appsemana03.presentation.viewmodel.HistorialViewModel


class AppContainer {
    val agregarViewModel by lazy {
        AgregarViewModel()
    }

    val buscarViewModel by lazy {
        BuscarViewModel()
    }

    val asignarViewModel by lazy {
        AsignarViewModel()
    }

    val historialViewModel by lazy {
        HistorialViewModel()
    }
}