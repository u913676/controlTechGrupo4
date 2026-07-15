package com.jnarvaez.appsemana03.data.remote.dto

import com.google.gson.annotations.SerializedName

data class InsertarEquipoRequest(
    @SerializedName("Equipo") val equipo: String,
    @SerializedName("Usuario_asignado") val usuarioAsignado: String,
    @SerializedName("Fecha_entrega") val fechaEntrega: String,
    @SerializedName("Obs") val obs: String,
    @SerializedName("Estado") val estado: String? = null,
    @SerializedName("Motivo_reparacion") val motivoReparacion: String? = null
)
