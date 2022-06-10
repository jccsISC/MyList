package com.jccsisc.mylist.domain.home

import com.jccsisc.mylist.common.core.DataState
import com.jccsisc.mylist.data.model.invitado.InvitadoModel

interface HomeRepo {
    suspend fun getInvitadosList(): DataState<List<InvitadoModel>>
}