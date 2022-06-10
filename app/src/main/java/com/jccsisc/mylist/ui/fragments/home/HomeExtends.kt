package com.jccsisc.mylist.ui.fragments.home

import androidx.navigation.fragment.findNavController
import com.jccsisc.mylist.R
import com.jccsisc.mylist.utils.LambdasObjet.changeTitle

fun HomeFragment.initElements() = with(mBinding) {

    changeTitle(getString(R.string.lista_de_invitados), false)

    initRV()

    configFirestorage()

    /*val list = arrayListOf(
        InvitadoModel("1", "Juan Francisco Rodriguez", "4531260729", "Apatzingán", "jf@hotmail.com", 1, 0, 1,"Alcohol", false, 0),
        InvitadoModel("2", "Juan Francisco Rodriguez", "4531260729", "Apatzingán", "jf@hotmail.com", 0, 0, 0,"Alcohol", false, 0),
        InvitadoModel("3", "Juan Francisco Rodriguez", "4531260729", "Apatzingán", "jf@hotmail.com", 1, 1, 0,"Alcohol", false, 0),
        InvitadoModel("4", "Juan Francisco Rodriguez", "4531260729", "Apatzingán", "jf@hotmail.com", 0, 1, 0,"Alcohol", false, 0),
        InvitadoModel("5", "Juan Francisco Rodriguez", "4531260729", "Apatzingán", "jf@hotmail.com", 1, 1, 1,"Alcohol", false, 0),
        InvitadoModel("6", "Juan Francisco Rodriguez", "4531260729", "Apatzingán", "jf@hotmail.com", 0, 0, 0,"Alcohol", false, 0),
    )*/

    /*adapter = HomeAdapter()
    rvTotalInvitados.adapter = adapter
    adapter.submitList(list)*/


    adapter.onClickItem = {
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
    }
}

fun HomeFragment.initObserversHome() = with(mBinding) {
    viewModel.apply {


    }
}