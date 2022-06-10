package com.jccsisc.mylist.ui.fragments.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.jccsisc.mylist.R
import com.jccsisc.mylist.databinding.FragmentDetailBinding


class DetailFragment : Fragment(R.layout.fragment_detail) {

    lateinit var mBinding: FragmentDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding = FragmentDetailBinding.bind(view)
        super.onViewCreated(mBinding.root, savedInstanceState)

        initElements()
    }

}