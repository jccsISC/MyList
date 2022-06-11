package com.jccsisc.mylist.ui.fragments.login

import android.util.Log
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.jccsisc.mylist.R
import com.jccsisc.mylist.common.base.BaseFragment
import com.jccsisc.mylist.common.core.DataState
import com.jccsisc.mylist.data.remote.login.LoginDataSource
import com.jccsisc.mylist.databinding.FragmentLoginBinding
import com.jccsisc.mylist.domain.login.LoginRepoImpl
import com.jccsisc.mylist.presentation.login.LoginVM
import com.jccsisc.mylist.presentation.login.LoginVMFactory
import com.jccsisc.mylist.ui.activities.home.HomeActivity
import com.jccsisc.mylist.utils.dialogs.DialogObject.showDialogObjet
import com.jccsisc.mylist.utils.goToActivity
import com.jccsisc.mylist.utils.setOnSingleClickListener
import com.jccsisc.mylist.utils.showView

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

    val viewModel by viewModels<LoginVM> {
        LoginVMFactory(LoginRepoImpl(LoginDataSource()))
    }

    override fun getLayout() = R.layout.fragment_login

    override fun initObservers() {  }

    override fun initView() { initElements() }

    fun login(email: String, password: String) = with(mBinding) {
        viewModel.loginVm(email, password).observe(viewLifecycleOwner) { result ->
            when(result) {
                is DataState.Loading -> {
                    showProgress()
                }
                is DataState.Success -> {
                    hideProgressBarCustom()
                    Log.d("data", "${result.data}")
                    goToHome()
                }
                is DataState.Failure -> {
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