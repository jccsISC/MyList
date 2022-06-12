package com.jccsisc.mylist.domain.auth

import com.google.firebase.auth.FirebaseUser
import com.jccsisc.mylist.data.model.user.UserModel
import com.jccsisc.mylist.data.remote.auth.AuthDataSource

class AuthRepoImpl(private val dataSource: AuthDataSource): AuthRepo {
    override suspend fun login(email: String, pws: String): FirebaseUser? = dataSource.login(email, pws)
    override suspend fun signIn(emal: String, pws: String, user: UserModel) = dataSource.signIn(emal, pws, user)
}