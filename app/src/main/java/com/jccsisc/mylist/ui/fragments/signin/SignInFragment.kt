package com.jccsisc.mylist.ui.fragments.signin

import android.graphics.drawable.Drawable
import android.view.View
import com.jccsisc.mylist.R
import com.jccsisc.mylist.common.base.BaseFragment
import com.jccsisc.mylist.databinding.FragmentSignInBinding
import eightbitlab.com.blurview.RenderScriptBlur


class SignInFragment : BaseFragment<FragmentSignInBinding, SignInVM>(SignInVM::class) {

    override fun getLayout() = R.layout.fragment_sign_in

    override fun initView() { initElements() }
}