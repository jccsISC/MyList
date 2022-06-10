package com.jccsisc.mylist.data.remote.home

import com.google.firebase.firestore.FirebaseFirestore
import com.jccsisc.mylist.common.constants.MyConstant.DB_INVITADOS
import com.jccsisc.mylist.common.core.DataState
import com.jccsisc.mylist.data.model.invitado.InvitadoModel
import kotlinx.coroutines.tasks.await

class HomeDataSource {
    suspend fun getInvitadosList(): DataState<List<InvitadoModel>> {
        val invitadoList = mutableListOf<InvitadoModel>()
        val querySnapshot = FirebaseFirestore.getInstance().collection(DB_INVITADOS).get().await()

        for (invitado in querySnapshot.documents) {
            invitado.toObject(InvitadoModel::class.java)?.let {
                invitadoList.add(it)
            }
        }

        return DataState.Success(invitadoList)
    }
}