package com.jccsisc.mylist.fragments.home.model

data class InvitadoModel(
    val id: Int = -1,
    val nombre: String = "",
    val telefono: String = "",
    val localidad: String = "",
    val correo: String = "",
    val parentezcp: Int = -1,
    val role: Int = -1,
    val esPadrinoDe: String = "",
    val invitadoAdicional: Boolean = false,
    val cantInvitadosAdicionales: Int = 0
)
