package com.jccsisc.mylist.ui.fragments.home

import android.widget.SearchView
import com.jccsisc.mylist.R
import com.jccsisc.mylist.common.core.MyResult
import com.jccsisc.mylist.utils.LambdasObjet.changeTitle
import com.jccsisc.mylist.utils.setColorNavBar
import com.jccsisc.mylist.utils.setOnSingleClickListener
import com.jccsisc.mylist.utils.showToast
import com.jccsisc.mylist.utils.showView
import java.util.*

fun HomeFragment.initElements() {

    mBinding.apply {
        activity?.setColorNavBar(R.color.primary_color)

        changeTitle(getString(R.string.lista_de_invitados), false)

        tvNoAsistiran.setOnSingleClickListener {
            closeSesion()
        }

        refresh.setOnRefreshListener {
            viewModel.fetchInvitadosList()
            refresh.isRefreshing = false
        }

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(nextText: String?): Boolean {
                if (nextText!!.isNotEmpty()) {
                    listAux.clear()

                    val search = nextText.lowercase(Locale.ROOT)

                    listInvitados.forEach {
                        if (it.nombre.lowercase(Locale.ROOT).contains(search) || it.numeroMesa.contains(search)) {
                            listAux.add(it)
                        }
                    }

                    initRV(listAux)
                    tvEmpty.showView(listAux.size == 0)
                } else {
                    listAux.clear()
                    listAux.addAll(listInvitados)
                    initRV(listAux)
                    tvEmpty.showView(listAux.size == 0)
                }

                return true
            }

            override fun onQueryTextSubmit(query: String?) = true
        })
    }
}

fun HomeFragment.initObserversHome() {
    mBinding.apply {
        viewModel.fetchInvitadosList().observe(viewLifecycleOwner) { result ->
            when (result) {
                is MyResult.Loading -> showProgress()
                is MyResult.Success -> {
                    hideProgressBarCustom()
                    refresh.showView()
                    tvEmpty.showView(false)
                    listInvitados.clear()
                    listInvitados.addAll(result.data)

                    tvCantidadInvitados.text = getString(R.string.total_invitados, listInvitados.size.toString())

                    val asistencia = listInvitados.filter { it.asistencia }
                    tvAsistiran.text = getString(R.string.num_invitados_presentes, asistencia.size.toString())

                    val faltantes = listInvitados.size - asistencia.size
                    tvNoAsistiran.text = getString(R.string.num_invitados_faltantes, faltantes.toString())

                    initRV(listInvitados)
                }
                is MyResult.Failure -> {
                    hideProgressBarCustom()
                    tvEmpty.showView(false)
                    showToast("Ocurrió un problema: ${result.exception}")
                }
            }
        }
    }
}