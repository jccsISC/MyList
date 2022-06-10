package com.jccsisc.mylist.utils.dialogs

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.airbnb.lottie.LottieDrawable
import com.jccsisc.mylist.R
import com.jccsisc.mylist.databinding.LoaderDialogFragmentBinding
import com.jccsisc.mylist.utils.setOnSingleClickListener
import eightbitlab.com.blurview.RenderScriptBlur

class LoaderFDialogFragment(
    private val showBlur: Boolean = true,
    private val radiusBlur: Float = 4f,
    private val textLoading: String? = null,
    private val lottieResourceCustom: Int? = R.raw.loader,
    private val cancelableDialog: Boolean = false
) : DialogFragment() {

    private lateinit var bindingDialog: LoaderDialogFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.StyleFullScreenDialog)
        isCancelable = cancelableDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = LoaderDialogFragmentBinding.inflate(inflater, container, false).apply {

        bindingDialog = this

        if (cancelableDialog) {
            zpDialogContainer.setOnSingleClickListener {
                dialog?.dismiss()
            }
        }

        lottieResourceCustom?.let {
            zpLottieView.setAnimation(lottieResourceCustom)
            zpLottieView.repeatCount = LottieDrawable.INFINITE
            zpLottieView.playAnimation()
        }

        zpTvLoading.text = textLoading ?: ""
        blurBackground(showBlur, radiusBlur)

    }.root

    override fun onStart() {
        super.onStart()
        if (dialog != null) {
            isCancelable = cancelableDialog
            val window: Window? = dialog!!.window
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog!!.window!!.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
        }
    }

    private fun blurBackground(showBlur: Boolean, radius: Float) {

        activity?.let {
            val decorView: View = it.window.decorView

            val windowBackground: Drawable = decorView.background

            bindingDialog.blurView.setupWith(decorView.findViewById(R.id.home_container_view))
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(RenderScriptBlur(context))
                .setBlurRadius(radius)
                .setBlurEnabled(showBlur)
                .setBlurAutoUpdate(true)
                .setHasFixedTransformationMatrix(false)
        }
    }

}