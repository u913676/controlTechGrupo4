package com.jnarvaez.appsemana03.domain.usecase

import com.jnarvaez.appsemana03.domain.model.Equipo
import com.jnarvaez.appsemana03.domain.repository.EquipoRepository

class InsertarEquipoUseCase(private val repository: EquipoRepository) {
    suspend operator fun invoke(equipo: Equipo) {
        repository.insertarEquipo(equipo)
    }
}
