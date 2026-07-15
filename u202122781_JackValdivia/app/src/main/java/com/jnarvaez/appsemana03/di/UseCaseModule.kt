package com.jnarvaez.appsemana03.di

import com.jnarvaez.appsemana03.domain.usecase.ActualizarEquipoUseCase
import com.jnarvaez.appsemana03.domain.usecase.EliminarEquipoUseCase
import com.jnarvaez.appsemana03.domain.usecase.EquipoUseCases
import com.jnarvaez.appsemana03.domain.usecase.GetEquipoUseCase
import com.jnarvaez.appsemana03.domain.usecase.GetEquiposUseCase
import com.jnarvaez.appsemana03.domain.usecase.InsertarEquipoUseCase

class UseCaseModule(repositoryModule: RepositoryModule) {
    val equipoUseCases by lazy {
        EquipoUseCases(
            GetEquiposUseCase(repositoryModule.equipoRepository),
            InsertarEquipoUseCase(repositoryModule.equipoRepository),
            GetEquipoUseCase(repositoryModule.equipoRepository),
            ActualizarEquipoUseCase(repositoryModule.equipoRepository),
            EliminarEquipoUseCase(repositoryModule.equipoRepository)
        )
    }
}
