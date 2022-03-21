package com.jccsisc.mylist.fragments.graphics

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.jccsisc.mylist.R
import com.jccsisc.mylist.databinding.FragmentGraphicsBinding

class GraphicsFragment : Fragment(R.layout.fragment_graphics) {

    lateinit var mBinding: FragmentGraphicsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding = FragmentGraphicsBinding.bind(view)
        super.onViewCreated(mBinding.root, savedInstanceState)

        initElements()
    }

}