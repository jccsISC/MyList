package com.jccsisc.mylist.data.model.invitado

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InvitadoModel(
    val id: String = "",
    val nombre: String = "",
    val telefono: String = "",
    val localidad: String = "",
    val numeroMesa: String = "0",
    val correo: String = "",
    val parentezcp: Int = -1,
    val role: Int = -1,
    var asistencia: Boolean = false,
    val esPadrinoDe: String = "",
    val invitadoAdicional: Boolean = false,
    val cantInvitadosAdicionales: Int = 0,
    var listAcompaniantes: List<InvitadoModel>? = null
) : Parcelable
