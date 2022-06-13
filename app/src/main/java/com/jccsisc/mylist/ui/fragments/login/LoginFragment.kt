package com.jccsisc.mylist.ui.fragments.login

import android.util.Log
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.jccsisc.mylist.R
import com.jccsisc.mylist.common.base.BaseFragment
import com.jccsisc.mylist.common.core.MyResult
import com.jccsisc.mylist.data.remote.auth.AuthDataSource
import com.jccsisc.mylist.databinding.FragmentLoginBinding
import com.jccsisc.mylist.domain.auth.AuthRepoImpl
import com.jccsisc.mylist.presentation.auth.AuthVM
import com.jccsisc.mylist.presentation.auth.AuthVMFactory
import com.jccsisc.mylist.ui.activities.home.HomeActivity
import com.jccsisc.mylist.utils.dialogs.DialogObject.showDialogObjet
import com.jccsisc.mylist.utils.goToActivity

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

    val viewModel by viewModels<AuthVM> {
        AuthVMFactory(AuthRepoImpl(AuthDataSource()))
    }

    override fun getLayout() = R.layout.fragment_login

    override fun initObservers() {  }

    override fun initView() { initElements() }

    fun validCredentials(correo: String, contra: String): Boolean {
        with(mBinding) {

            if (correo.isEmpty()) {
                tieEmail.error = "El correo está vacío"
                return true
            }

            if (contra.isEmpty()) {
                tiePws.error = "La contraseña está vacía"
                return true
            }
        }

        return false
    }

    fun login(email: String, password: String) = with(mBinding) {
        viewModel.loginVM(email, password).observe(viewLifecycleOwner) { result ->
            when(result) {
                is MyResult.Loading -> showProgress()
                is MyResult.Success -> {
                    hideProgressBarCustom()
                    Log.d("data", "${result.data}")
                    goToHome()
                }
                is MyResult.Failure -> {
                    hideProgressBarCustom()
                    showDialogObjet(
                        requireActivity(),
                        "Ubo un error al autenticarte",
                        "${result.exception}"
                    )
                }
            }
        }
    }

    private fun goToHome() {
        activity?.let {
            it.goToActivity<HomeActivity>()
            it.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            it.finish()
        }
    }
}