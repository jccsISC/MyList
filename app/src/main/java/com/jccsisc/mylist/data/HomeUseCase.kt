package com.jccsisc.mylist.data

import com.jccsisc.mylist.common.core.DataState
import com.jccsisc.mylist.data.model.InvitadoModel
import kotlinx.coroutines.flow.Flow

interface HomeUseCase {
    suspend fun getListInvitados(): Flow<DataState<InvitadoModel>>
}