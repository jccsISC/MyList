package com.jccsisc.mylist.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.jccsisc.mylist.R
import kotlin.reflect.KClass

abstract class BaseFragment<B: ViewDataBinding> : Fragment() {

    lateinit var mBinding: B

    lateinit var zpBaseView: BaseView

    //val viewModel by lazy { ViewModelProvider(this).get(vkClass.javaObjectType) }


    abstract fun getLayout(): Int
    open fun initObservers() {  }
    abstract fun initView()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater,getLayout(), container, false)

        if (activity is BaseView) {
            zpBaseView = activity as BaseView
        }

        initObservers()
        initView()

        return mBinding.root
    }


    fun showProgress(
        showBlur: Boolean = true,
        radiusBlur: Float = 4f,
        message: String? = null,
        isCancelable: Boolean = false,
        lottieResourceCustom: Int? = R.raw.loader
    ) {
        zpBaseView.showProgressBlurOrNormal(
            showBlur,
            radiusBlur,
            message,
            isCancelable,
            lottieResourceCustom
        )
    }

    fun hideProgressBarCustom() {
        zpBaseView.hideProgress()
    }

    fun hideKeyboard() {
        zpBaseView.hideKeyBoard()
    }

    fun showLottie(
        lottie: LottieAnimationView,
        containerPersonality: View,
        containerParent: View,
        show: Boolean
    ) {
        zpBaseView.showLottie(
            lottie,
            containerPersonality,
            containerParent,
            show
        )
    }
}