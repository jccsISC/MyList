package com.jccsisc.mylist.data.remote.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.jccsisc.mylist.common.constants.MyConstant.DB_USERS
import com.jccsisc.mylist.data.model.user.UserModel
import kotlinx.coroutines.tasks.await

class AuthDataSource {
    suspend fun login(email: String, password: String): FirebaseUser? {
        val authResult =
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
        return authResult.user
    }

    suspend fun signIn(email: String, password: String, user: UserModel): FirebaseUser? {
        val authResult =
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()

        authResult.user?.uid?.let { uid ->
            FirebaseFirestore
                .getInstance()
                .collection(DB_USERS)
                .document(uid)
                .set(user)
                .await()
        }

        return authResult.user
    }


}