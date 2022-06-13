package com.jccsisc.mylist.data.remote.add

import com.google.firebase.firestore.FirebaseFirestore
import com.jccsisc.mylist.common.constants.MyConstant.DB_INVITADOS
import com.jccsisc.mylist.data.model.invitado.InvitadoModel
import kotlinx.coroutines.tasks.await

class AddDataSource {
    suspend fun addInvitado(invitadoModel: InvitadoModel) {
       FirebaseFirestore
            .getInstance()
            .collection(DB_INVITADOS)
            .add(invitadoModel)
            .await()
    }
}