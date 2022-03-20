package com.jccsisc.mylist.fragments.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.jccsisc.mylist.R
import com.jccsisc.mylist.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var mBinding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding = FragmentHomeBinding.bind(view)
        super.onViewCreated(mBinding.root, savedInstanceState)

        initElements()

    }

}