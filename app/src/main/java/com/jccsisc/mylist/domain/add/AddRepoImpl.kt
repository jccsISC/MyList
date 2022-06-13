package com.jccsisc.mylist.domain.add

import com.jccsisc.mylist.data.model.invitado.InvitadoModel
import com.jccsisc.mylist.data.remote.add.AddDataSource

class AddRepoImpl(private val addDataSource: AddDataSource): AddRepo {
    override suspend fun addInvidato(invitado: InvitadoModel) = addDataSource.addInvitado(invitado)
}