package com.jccsisc.mylist.domain.login

import com.google.firebase.auth.FirebaseUser
import com.jccsisc.mylist.data.remote.login.LoginDataSource

class LoginRepoImpl(private val dataSource: LoginDataSource): LoginRepo {
    override suspend fun login(email: String, password: String): FirebaseUser? = dataSource.login(email, password)
}