package com.jccsisc.mylist.domain.login

import com.google.firebase.auth.FirebaseUser

interface LoginRepo {
    suspend fun login(email: String, password: String): FirebaseUser?
}