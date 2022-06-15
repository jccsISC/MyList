package com.jccsisc.mylist.ui.fragments.detail

import androidx.navigation.fragment.navArgs
import com.jccsisc.mylist.R
import com.jccsisc.mylist.common.base.BaseFragment
import com.jccsisc.mylist.databinding.FragmentDetailBinding


class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    val args by navArgs<DetailFragmentArgs>()

    override fun getLayout() = R.layout.fragment_detail

    override fun initView() {
        initElements()
    }

}