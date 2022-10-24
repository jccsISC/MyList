package com.jccsisc.mylist.ui.activities.splash

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.jccsisc.mylist.R
import com.jccsisc.mylist.common.base.BaseActivity
import com.jccsisc.mylist.databinding.ActivitySplashBinding
import com.jccsisc.mylist.ui.activities.login.LoginActivity
import com.jccsisc.mylist.utils.goToActivity
import com.jccsisc.mylist.utils.setColorNavBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity<ActivitySplashBinding>() {


    override fun getLayout() = R.layout.activity_splash

    override fun initView(): Unit = with(mBinding) {
        setColorNavBar(Color.TRANSPARENT)

        tvCarmen.animation = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.slide_up)
        tvY.animation = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.slide_in_left_2)
        tvJulio.animation = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.slide_down)

        Handler(Looper.getMainLooper()).postDelayed({
            goToActivity<LoginActivity>()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }, 2500)
    }
}