package com.martinez.appitasset.presentation.navigation


object NavRoutes {
    const val HOME = "homeRoute"
    const val ITASSETS = "itassetsRoute"
    const val REGISTER = "registerRoute"
    const val HISTORY = "historyRoute"
    const val PROFILE = "profileRoute"
    const val VIEW = "viewRoute"

    fun getTitle(route: String?): String {
        return when {
            route == HOME -> "ControlTech"
            route == ITASSETS -> "ControlTech"
            route == REGISTER -> "ControlTech"
            route == HISTORY -> "ControlTech"
            route == PROFILE -> "ControlTech"
            route?.startsWith(VIEW) == true -> "ControlTech"
            else -> "ControlTech"
        }
    }

}