package com.jccsisc.mylist.ui.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import com.jccsisc.mylist.R
import com.jccsisc.mylist.databinding.FragmentAddBinding
import com.jccsisc.mylist.utils.showToast

class AddFragment : Fragment(R.layout.fragment_add), AdapterView.OnItemClickListener {

    lateinit var mBinding: FragmentAddBinding
    var progreso = 0

    val viewModel by viewModels<AddViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding = FragmentAddBinding.bind(view)
        super.onViewCreated(mBinding.root, savedInstanceState)

        initObserversAdd()
        initElements()
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position).toString()

        showToast("CLICK: $item")
    }
}