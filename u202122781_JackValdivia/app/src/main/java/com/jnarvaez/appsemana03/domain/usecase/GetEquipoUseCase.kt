package com.jnarvaez.appsemana03.domain.usecase

import com.jnarvaez.appsemana03.domain.model.Equipo
import com.jnarvaez.appsemana03.domain.repository.EquipoRepository

class GetEquipoUseCase(private val repository: EquipoRepository) {
    suspend operator fun invoke(id: String): Equipo {
        return repository.getEquipoById(id)
    }
}
