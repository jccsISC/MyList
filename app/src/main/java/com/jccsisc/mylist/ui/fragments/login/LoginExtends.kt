package com.jccsisc.mylist.ui.fragments.login

import android.app.Activity.RESULT_OK
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.jccsisc.mylist.R
import com.jccsisc.mylist.ui.activities.home.HomeActivity
import com.jccsisc.mylist.utils.goToActivity
import com.jccsisc.mylist.utils.setOnSingleClickListener

fun LoginFragment.initElements() = with(mBinding) {

    btnBack.setOnSingleClickListener { findNavController().popBackStack() }

    configAuth()

    btnLogin.setOnSingleClickListener {
        activity?.let {
            it.goToActivity<HomeActivity>()
            it.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            it.finish()
        }
    }
}