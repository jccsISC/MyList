package com.jccsisc.mylist.ui.activities.home

import androidx.navigation.findNavController
import com.jccsisc.mylist.R
import com.jccsisc.mylist.utils.LambdasObjet
import com.jccsisc.mylist.utils.setColorNavBar
import com.jccsisc.mylist.utils.showView

fun HomeActivity.initElements() = with(mBinding){

    LambdasObjet.changeTitle = { title, back ->

        tvTitle.text = title

        if (back) {
            isHomeView = false
            imgGraficas.setImageResource(R.drawable.ic_back)
            imgAgregar.showView(false)
        } else {
            isHomeView = true
            imgAgregar.showView()
            imgGraficas.setImageResource(R.drawable.ic_graficas)
        }
    }

    imgGraficas.setOnClickListener {
        if (isHomeView) {
            findNavController(R.id.fragmentContainerView).navigate(R.id.action_homeFragment_to_graphicsFragment)
        } else {
            findNavController(R.id.fragmentContainerView).popBackStack()
        }
    }

    imgAgregar.setOnClickListener {
        findNavController(R.id.fragmentContainerView).navigate(R.id.action_homeFragment_to_addFragment)
    }
}