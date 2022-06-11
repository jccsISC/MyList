package com.jccsisc.mylist.ui.fragments.presentation

import androidx.navigation.fragment.findNavController
import com.jccsisc.mylist.R
import com.jccsisc.mylist.utils.setOnSingleClickListener

fun PresentationFragment.initElements() = with(mBinding) {
    blurBackground(true)

    isUserLoggeIn()

    btnLogin.setOnSingleClickListener {
        findNavController().navigate(R.id.action_presentationFragment_to_loginFragment)
    }

    btnSignIn.setOnSingleClickListener {
        findNavController().navigate(R.id.action_presentationFragment_to_signInFragment)
    }
}