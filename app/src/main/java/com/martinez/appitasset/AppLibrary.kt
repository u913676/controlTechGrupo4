package com.martinez.appitasset

import android.app.Application
import com.martinez.appitasset.di.AppContainer

class AppLibrary: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer()
    }
}