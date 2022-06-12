package com.jccsisc.mylist.data.model.user

import com.jccsisc.mylist.common.constants.MyConstant.ORDINAL_USER

data class UserModel(
    val name: String = "",
    val role: String = ORDINAL_USER,
    val image: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val permissions: Boolean = false
)
