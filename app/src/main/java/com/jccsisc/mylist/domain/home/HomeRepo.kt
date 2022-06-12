package com.jccsisc.mylist.domain.home

import com.jccsisc.mylist.common.core.MyResult
import com.jccsisc.mylist.data.model.invitado.InvitadoModel

interface HomeRepo {
    suspend fun getInvitadosList(): MyResult<List<InvitadoModel>>
}