package com.jnarvaez.appsemana03.domain.usecase

data class EquipoUseCases(
    val getEquipos: GetEquiposUseCase,
    val insertarEquipo: InsertarEquipoUseCase,
    val getEquipo: GetEquipoUseCase,
    val actualizarEquipo: ActualizarEquipoUseCase,
    val eliminarEquipo: EliminarEquipoUseCase
)
