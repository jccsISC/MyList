package com.jccsisc.mylist.common.base

import android.view.View
import androidx.annotation.RawRes
import com.airbnb.lottie.LottieAnimationView
import com.jccsisc.mylist.R

interface BaseView {
    fun showProgressBlurOrNormal(
        showBlur: Boolean = true,
        radiusBlur: Float = 4f,
        message: String? = null,
        isCancelable: Boolean = false,
        @RawRes lottieResource: Int? = R.raw.loader
    )

    fun hideProgress()
    fun hideKeyBoard()
    fun showKeyBoard(viewEditable: View)
    fun showLottie(
        lottie: LottieAnimationView,
        containerPersonality: View,
        containerParent: View, show: Boolean
    )
}