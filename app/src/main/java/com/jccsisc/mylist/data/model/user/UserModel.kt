package com.jccsisc.mylist.data.model.user

data class UserModel(
    val id: String,
    val name: String,
    val role: String,
    val image: String,
    val email: String,
    val phoneNumber: String,
    val permissions: Boolean
)
