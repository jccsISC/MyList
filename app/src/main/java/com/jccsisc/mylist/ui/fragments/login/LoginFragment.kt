package com.jccsisc.mylist.ui.fragments.login

import com.google.firebase.auth.FirebaseAuth
import com.jccsisc.mylist.R
import com.jccsisc.mylist.common.base.BaseFragment
import com.jccsisc.mylist.databinding.FragmentLoginBinding
import com.jccsisc.mylist.ui.activities.home.HomeActivity
import com.jccsisc.mylist.utils.goToActivity
import com.jccsisc.mylist.utils.setOnSingleClickListener

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun getLayout() = R.layout.fragment_login

    override fun initView() { initElements() }

    fun isUserLoggeIn() {
        firebaseAuth.currentUser?.let {
            goToHome()
        }
    }

    fun doLogin() = with(mBinding) {
        val correo = tieEmail.text.toString().trim()
        val contra = tiePws.text.toString().trim()

        btnLogin.setOnSingleClickListener {
            validCredentials(contra, contra)
            login(correo, contra)
        }
    }

    private fun validCredentials(email: String, password: String) = with(mBinding) {
        if (email.isEmpty()) {
            tieEmail.error = "El correo está vacío"
            return@with
        }

        if (password.isEmpty()) {
            tiePws.error = "La contraseña está vacía"
            return@with
        }
    }

    fun login(email: String, password: String) {

    }

    fun goToHome() {
        activity?.let {
            it.goToActivity<HomeActivity>()
            it.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            it.finish()
        }
    }
}