package com.jnarvaez.appsemana03.domain.usecase

import com.jnarvaez.appsemana03.domain.repository.EquipoRepository

class EliminarEquipoUseCase(private val repository: EquipoRepository) {
    suspend operator fun invoke(id: String) {
        repository.eliminarEquipo(id)
    }
}
