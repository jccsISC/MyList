package com.jccsisc.mylist.ui.fragments.presentation

import android.graphics.drawable.Drawable
import android.view.View
import com.jccsisc.mylist.R
import com.jccsisc.mylist.common.base.BaseFragment
import com.jccsisc.mylist.databinding.FragmentPresentationBinding
import eightbitlab.com.blurview.RenderScriptBlur

class PresentationFragment : BaseFragment<FragmentPresentationBinding, PresentationVM>(PresentationVM::class) {

    override fun getLayout() = R.layout.fragment_presentation

    override fun initView() { initElements() }

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