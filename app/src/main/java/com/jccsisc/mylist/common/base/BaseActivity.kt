package com.jccsisc.mylist.common.base

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.airbnb.lottie.LottieAnimationView
import com.jccsisc.mylist.R
import com.jccsisc.mylist.utils.dialogs.LoaderFDialogFragment
import com.jccsisc.mylist.utils.setColor
import com.jccsisc.mylist.utils.showView

abstract class BaseActivity<B: ViewDataBinding> : AppCompatActivity(), BaseView {

    val mBinding: B by lazy { DataBindingUtil.setContentView(this, getLayout()) as B }

    private lateinit var dialogLoader: LoaderFDialogFragment

    abstract fun getLayout(): Int
    open fun initObservers() {}
    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    override fun showProgressBlurOrNormal(showBlur:Boolean, radiusBlur: Float, message: String?, isCancelable: Boolean, lottieResource: Int?) {

        if (!this::dialogLoader.isInitialized) {
            dialogLoader = LoaderFDialogFragment(
                showBlur,
                radiusBlur,
                message,
                lottieResource,
                isCancelable
            )
        }

        if (!dialogLoader.isVisible) {
            dialogLoader.show(supportFragmentManager, LoaderFDialogFragment::class.java.simpleName)
        }
    }

    override fun hideProgress() {
        if (this::dialogLoader.isInitialized) {
            dialogLoader.dismiss()
        }
    }

    override fun showKeyBoard(viewEditable: View) {
        if (currentFocus != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(viewEditable, InputMethodManager.SHOW_IMPLICIT)
        }
    }


    override fun hideKeyBoard() {
        if (currentFocus != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }


    override fun showLottie(
        lottie: LottieAnimationView,
        containerPersonality: View,
        containerParent: View,
        show: Boolean
    ) {
        if (show) {
            hideKeyBoard()
            lottie.showView()
            containerPersonality.showView(false)
            containerParent.setBackgroundColor(
                setColor(
                    applicationContext,
                    R.color.white
                )
            )
        } else {
            lottie.showView(false)
            containerPersonality.showView()
            //containerParent.setBackgroundColor(setColor(applicationContext, R.color.z_payroll_gray))
        }
    }

    /**
     * Funcion que anula la configuracion del sistema para cambiar el tamaño de las fuente
     */
    private fun adjustFontScale(configuration: Configuration, scale: Float) {
        configuration.fontScale = scale
        val metrics = resources.displayMetrics
        val wm = getSystemService(WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(metrics)
        metrics.scaledDensity = configuration.fontScale * metrics.density
        baseContext.resources.updateConfiguration(configuration, metrics)
    }
}