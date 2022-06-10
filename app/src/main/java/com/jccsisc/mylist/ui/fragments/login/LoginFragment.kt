package com.jccsisc.mylist.ui.fragments.login

import com.jccsisc.mylist.R
import com.jccsisc.mylist.common.base.BaseFragment
import com.jccsisc.mylist.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginVM>(LoginVM::class) {

    override fun getLayout() = R.layout.fragment_login

    override fun initView() { initElements() }
}