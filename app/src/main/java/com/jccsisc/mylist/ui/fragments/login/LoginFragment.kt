package com.jccsisc.mylist.ui.fragments.login

import android.app.Activity
import androidx.activity.result.contract.ActivityResultContracts
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.jccsisc.mylist.R
import com.jccsisc.mylist.common.base.BaseFragment
import com.jccsisc.mylist.databinding.FragmentLoginBinding
import com.jccsisc.mylist.ui.activities.home.HomeActivity
import com.jccsisc.mylist.utils.goToActivity

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginVM>(LoginVM::class) {

    lateinit var firebaseAuth: FirebaseAuth
    lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun getLayout() = R.layout.fragment_login

    override fun initView() { initElements() }

    fun configAuth() {

        firebaseAuth = FirebaseAuth.getInstance()
        authStateListener = FirebaseAuth.AuthStateListener { auth ->
            if (auth.currentUser != null) {
                requireActivity().goToActivity<HomeActivity>()
            } else {

                val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())

                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    val response = IdpResponse.fromResultIntent(it.data)

                    if (it.resultCode == Activity.RESULT_OK) {
                        val user = FirebaseAuth.getInstance()
                        if (user != null) {
                            requireActivity().goToActivity<HomeActivity>()
                        }
                    }

                }.launch(
                    AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    override fun onPause() {
        super.onPause()
        firebaseAuth.removeAuthStateListener(authStateListener)
    }
}