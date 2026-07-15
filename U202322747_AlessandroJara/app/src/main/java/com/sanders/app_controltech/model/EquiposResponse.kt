package com.sanders.app_controltech.model

data class EquiposResponse(
    val success: Boolean,
    val data: EquiposData,
    val timestamp: String
)

data class EquiposData(
    val items: List<Equipo>,
    val nextKey: String?
)