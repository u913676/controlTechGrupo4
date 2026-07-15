package com.jnarvaez.appsemana03

import android.app.Application
import com.jnarvaez.appsemana03.di.AppContainer

class LibroApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer()
    }
}
