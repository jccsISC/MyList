package com.jccsisc.mylist.ui.fragments.signin

import android.util.Log
import androidx.fragment.app.viewModels
import com.jccsisc.mylist.R
import com.jccsisc.mylist.common.base.BaseFragment
import com.jccsisc.mylist.common.core.MyResult
import com.jccsisc.mylist.data.model.user.UserModel
import com.jccsisc.mylist.data.remote.auth.AuthDataSource
import com.jccsisc.mylist.databinding.FragmentSignInBinding
import com.jccsisc.mylist.domain.auth.AuthRepoImpl
import com.jccsisc.mylist.presentation.auth.AuthVM
import com.jccsisc.mylist.presentation.auth.AuthVMFactory
import com.jccsisc.mylist.ui.activities.home.HomeActivity
import com.jccsisc.mylist.utils.dialogs.DialogObject
import com.jccsisc.mylist.utils.goToActivity


class SignInFragment : BaseFragment<FragmentSignInBinding>() {

    val viewModel by viewModels<AuthVM> {
        AuthVMFactory(AuthRepoImpl(AuthDataSource()))
    }

    override fun getLayout() = R.layout.fragment_sign_in

    override fun initView() { initElements() }

    fun validCredentials(name: String, num: String, email: String, pws: String, pwsC: String): Boolean {
        with(mBinding) {

            if (name.isEmpty()) {
                tieName.error = "El nombre está vacío"; return true
            }
            if (num.isEmpty()) {
                tieNumber.error = "El número está vacío"; return true
            }
            if (email.isEmpty()) {
                tieEmail.error = "El correo está vacío"; return true
            }
            if (pws.isEmpty()) {
                tiePws.error = "La contraseña está vacía"; return true
            }
            if (pwsC.isEmpty()) {
                tiePwsConfirm.error = "Debe confirmar la contraseña"; return true
            }
        }

        return false
    }

    fun createUser(email: String, pws: String, user: UserModel) {
        viewModel.signInVM(email, pws, user).observe(viewLifecycleOwner) { result->
            when(result) {
                is MyResult.Loading -> showProgress()
                is MyResult.Success -> {
                    hideProgressBarCustom()
                    Log.d("data", "${result.data}")
                    activity?.let {
                        it.goToActivity<HomeActivity>()
                        it.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                        it.finish()
                    }
                }
                is MyResult.Failure -> {
                    hideProgressBarCustom()
                    DialogObject.showDialogObjet(
                        requireActivity(),
                        "Ocurrió un error al intenar registrarte",
                        "${result.exception}"
                    )
                }
            }
        }
    }
}