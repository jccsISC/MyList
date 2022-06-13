package com.jccsisc.mylist.domain.add

import com.jccsisc.mylist.data.model.invitado.InvitadoModel

interface AddRepo {
    suspend fun addInvidato(invitado: InvitadoModel)
}