package com.jccsisc.mylist.ui.fragments.signin

import androidx.compose.ui.text.capitalize
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.data.model.User
import com.jccsisc.mylist.R
import com.jccsisc.mylist.data.model.user.UserModel
import com.jccsisc.mylist.ui.activities.home.HomeActivity
import com.jccsisc.mylist.utils.goToActivity
import com.jccsisc.mylist.utils.setOnSingleClickListener
import java.util.*

fun SignInFragment.initElements() = with(mBinding) {

    btnBack.setOnSingleClickListener { findNavController().popBackStack() }

    btnSingIn.setOnSingleClickListener {
        /*activity?.let {
            it.goToActivity<HomeActivity>()
            it.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            it.finish()
        }*/

        val name = tieName.text.toString()
        val phoneNumber = tieNumber.text.toString()
        val email = tieEmail.text.toString()
        val pwd = tiePws.text.toString()
        val pwdConfirm = tiePwsConfirm.text.toString()

        if (validCredentials(name, phoneNumber, email, pwd, pwdConfirm)) return@setOnSingleClickListener

        if (pwd != pwdConfirm) {
            tiePws.error = "La contraseña no coincide"
            tiePwsConfirm.error = "La contraseña no coincide"
            return@setOnSingleClickListener
        }

        val user = UserModel(
            name = name,
            email = email,
            phoneNumber = phoneNumber
        )

        createUser(email, pwd, user)
    }
}