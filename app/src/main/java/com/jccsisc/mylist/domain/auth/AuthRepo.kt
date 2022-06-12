package com.jccsisc.mylist.domain.auth

import com.google.firebase.auth.FirebaseUser
import com.jccsisc.mylist.data.model.user.UserModel

interface AuthRepo {
    suspend fun login(email: String, pws: String): FirebaseUser?
    suspend fun signIn(emal: String, pws: String, user: UserModel): FirebaseUser?
}