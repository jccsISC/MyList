package com.jccsisc.mylist.ui.fragments.login

import androidx.navigation.fragment.findNavController
import com.jccsisc.mylist.R
import com.jccsisc.mylist.ui.activities.home.HomeActivity
import com.jccsisc.mylist.utils.goToActivity
import com.jccsisc.mylist.utils.setOnSingleClickListener

fun LoginFragment.initElements() = with(mBinding) {

    btnBack.setOnSingleClickListener { findNavController().popBackStack() }

    //isUserLoggeIn()
    //doLogin()
    btnLogin.setOnSingleClickListener { goToHome() }
}