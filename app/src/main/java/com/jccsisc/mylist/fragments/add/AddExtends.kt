package com.jccsisc.mylist.fragments.add

import android.widget.ArrayAdapter
import com.jccsisc.mylist.R
import com.jccsisc.mylist.utils.LambdasObjet

fun AddFragment.initElements() {
    mBinding.apply {

        LambdasObjet.changeTitle("Agregar invitado", true)

        val padrinoDe = resources.getStringArray(R.array.padrino_de)
        val adapterPadrinos = ArrayAdapter(requireContext(), R.layout.item_list_padrinos, padrinoDe)

        spOpciones.apply {
            setAdapter(adapterPadrinos)
            onItemClickListener = this@initElements
        }

        btnGuardar.setOnClickListener {
            if (progreso <= 90) {
                progreso += 10
                viewModel.setPorcentaje(progreso)
            }
        }

    }
}

fun AddFragment.initObserversAdd() {
    mBinding.apply {

        with(viewModel) {
            progreso.observe(viewLifecycleOwner) {
                tvProgreso.text = "$it%"
            }
        }

    }
}