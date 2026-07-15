package com.jnarvaez.appsemana03.domain.usecase

import com.jnarvaez.appsemana03.domain.repository.EquipoRepository

class GetEquiposUseCase(private val repository: EquipoRepository) {
    suspend operator fun invoke() = repository.getEquipos()
}
