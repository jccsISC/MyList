package com.jccsisc.mylist.ui.fragments.add

import android.util.Log
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.jccsisc.mylist.R
import com.jccsisc.mylist.common.core.MyResult
import com.jccsisc.mylist.data.model.invitado.InvitadoModel
import com.jccsisc.mylist.ui.activities.home.HomeActivity
import com.jccsisc.mylist.utils.LambdasObjet
import com.jccsisc.mylist.utils.dialogs.DialogObject
import com.jccsisc.mylist.utils.goToActivity
import com.jccsisc.mylist.utils.showToast
import com.jccsisc.mylist.utils.showView
import java.util.*

fun AddFragment.initElements() {
    mBinding.apply {

        LambdasObjet.changeTitle("Agregar invitado", true)

        viewModel.progreso.observe(viewLifecycleOwner) {
            tvProgreso.text = "$it%"
        }

        rgParentezco.setOnCheckedChangeListener { _, i ->
            parentezco = if (i == rbFamilia.id) {
                0
            } else {
                1
            }
        }

        val spPadrinoDe = resources.getStringArray(R.array.padrino_de)
        val adapterPadrinos = ArrayAdapter(requireContext(), R.layout.item_list_padrinos, spPadrinoDe)

        spOpciones.apply {
            setAdapter(adapterPadrinos)
            onItemClickListener = this@initElements
        }

        rgRole.setOnCheckedChangeListener { _, i ->
            role = if (i == rbPadrino.id) {
                tilOpciones.showView()
                0
            } else {
                tilOpciones.showView(false)
                1
            }
        }

        val spNumeroMesa = resources.getStringArray(R.array.numero_mesas)
        val adapterNumeroMesas = ArrayAdapter(requireContext(), R.layout.item_list_padrinos, spNumeroMesa)

        spMesas.apply {
            setAdapter(adapterNumeroMesas)
            onItemClickListener = this@initElements
        }

        btnGuardar.setOnClickListener {

            val nombre = tieNombre.text.toString()
            val telefono = tieTelefono.text.toString()
            val localidad = tieLocalidad.text.toString()
            val correo = tieCorreo.text.toString()

            if (validData(
                    nombre,
                    telefono,
                    localidad,
                    correo,
                    (!rbFamilia.isChecked && !rbAmigo.isChecked),
                    (!rbPadrino.isChecked && !rbInvitado.isChecked)
                )
            ) return@setOnClickListener


            if (progreso <= 90) {
                progreso += 10
                viewModel.setPorcentaje(progreso)
            }

            if (padrinoDe.isNullOrEmpty() && rbPadrino.isChecked) {
                showToast("Selecciona de qué es padrino $nombre")
                return@setOnClickListener
            } else {
                padrinoDe = ""
            }

            if (numeroMesa.isNullOrEmpty()) {
                showToast("Selecciona un número de mesa para $nombre")
                return@setOnClickListener
            }

            val invitadoModel = InvitadoModel(
                UUID.randomUUID().toString(),
                nombre,
                telefono,
                localidad,
                numeroMesa,
                correo,
                parentezco,
                role,
                0,
                padrinoDe,
                false,
                0
            )

            viewModel.addInviadoVM(invitadoModel).observe(viewLifecycleOwner) { result ->
                when (result) {
                    is MyResult.Loading -> showProgress()
                    is MyResult.Success -> {
                        hideProgressBarCustom()
                        Log.d("data", "${result.data}")
                        findNavController().popBackStack()
                    }
                    is MyResult.Failure -> {
                        hideProgressBarCustom()
                        DialogObject.showDialogObjet(
                            requireActivity(),
                            "Ocurrió un error al intenar registrarte",
                            "${result.exception}"
                        )
                    }
                }
            }
        }

    }
}