package com.jccsisc.mylist.ui.fragments.detail

import com.jccsisc.mylist.ui.fragments.detail.adapter.DetailAdapter
import com.jccsisc.mylist.utils.LambdasObjet

fun DetailFragment.initElements() = with(mBinding) {
    LambdasObjet.changeTitle("Detalle", true)

    args.invitadoModel.apply {
        tvNumMesa.text = numeroMesa
        tvNameInvitado.text = nombre

        val adapter = DetailAdapter()
        rvAcompanantes.adapter = adapter
        adapter.submitList(listAcompañantes)
    }
}