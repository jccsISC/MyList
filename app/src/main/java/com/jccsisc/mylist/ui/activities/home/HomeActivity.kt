package com.jccsisc.mylist.ui.activities.home

import com.jccsisc.mylist.R
import com.jccsisc.mylist.common.base.BaseActivity
import com.jccsisc.mylist.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    var isHomeView = true

    override fun getLayout() = R.layout.activity_home

    override fun initView()  {
        initElements()
    }
}