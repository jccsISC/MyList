package com.jccsisc.mylist.ui.fragments.presentation

import android.graphics.drawable.Drawable
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.jccsisc.mylist.R
import com.jccsisc.mylist.common.base.BaseFragment
import com.jccsisc.mylist.databinding.FragmentPresentationBinding
import com.jccsisc.mylist.ui.activities.home.HomeActivity
import com.jccsisc.mylist.utils.goToActivity
import eightbitlab.com.blurview.RenderScriptBlur

class PresentationFragment : BaseFragment<FragmentPresentationBinding>() {

    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun getLayout() = R.layout.fragment_presentation

    override fun initView() { initElements() }

    fun isUserLoggeIn() {
        firebaseAuth.currentUser?.let {
            activity?.let {
                it.goToActivity<HomeActivity>()
                it.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                it.finish()
            }
        }
    }

    fun blurBackground(showBlur: Boolean) {
        val radius = 18f

        activity?.let {
            val decorView: View =  it.window.decorView

            val windowBackground: Drawable = decorView.background

            mBinding.blurView.setupWith(decorView.findViewById(R.id.home_container_view_login))
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(RenderScriptBlur(requireContext()))
                .setBlurRadius(radius)
                .setBlurEnabled(showBlur)
                .setBlurAutoUpdate(true)
                .setHasFixedTransformationMatrix(false)
        }
    }
}