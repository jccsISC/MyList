package com.jccsisc.mylist.ui.fragments.signin

import com.jccsisc.mylist.R
import com.jccsisc.mylist.common.base.BaseFragment
import com.jccsisc.mylist.databinding.FragmentSignInBinding


class SignInFragment : BaseFragment<FragmentSignInBinding>() {

    override fun getLayout() = R.layout.fragment_sign_in

    override fun initView() { initElements() }
}