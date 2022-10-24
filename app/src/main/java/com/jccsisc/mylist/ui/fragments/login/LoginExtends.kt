package com.jccsisc.mylist.ui.fragments.login

import androidx.navigation.fragment.findNavController
import com.jccsisc.mylist.R
import com.jccsisc.mylist.ui.activities.home.HomeActivity
import com.jccsisc.mylist.utils.goToActivity
import com.jccsisc.mylist.utils.setOnSingleClickListener

fun LoginFragment.initElements() {
    mBinding.apply {

        btnBack.setOnSingleClickListener { findNavController().popBackStack() }

        btnLogin.setOnSingleClickListener {
            val correo = tieEmail.text.toString().trim()
            val contra = tiePws.text.toString().trim()

            if (validCredentials(correo, contra)) return@setOnSingleClickListener
            login(correo, contra)
        }
    }
}
