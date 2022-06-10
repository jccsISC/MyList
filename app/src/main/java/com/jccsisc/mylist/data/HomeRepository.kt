package com.jccsisc.mylist.data

import com.jccsisc.mylist.common.core.DataState
import com.jccsisc.mylist.data.model.InvitadoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class HomeRepository @Inject constructor(): HomeUseCase {

    override suspend fun getListInvitados(): Flow<DataState<InvitadoModel>> = flow {
        emit(DataState.Loading)

        try {
            val isSuccessFull = false


        } catch (e: Exception) {

        }
    }

}