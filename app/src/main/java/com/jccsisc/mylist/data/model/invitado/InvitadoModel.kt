package com.jccsisc.mylist.data.model.invitado

data class InvitadoModel(
    val id: String = "",
    val nombre: String = "",
    val telefono: String = "",
    val localidad: String = "",
    val numeroMesa: String = "0",
    val correo: String = "",
    val parentezcp: Int = -1,
    val role: Int = -1,
    val asistencia: Int = 0,
    val esPadrinoDe: String = "",
    val invitadoAdicional: Boolean = false,
    val cantInvitadosAdicionales: Int = 0
)
