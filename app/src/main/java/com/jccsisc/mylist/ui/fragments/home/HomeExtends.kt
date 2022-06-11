package com.jccsisc.mylist.ui.fragments.home

import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.jccsisc.mylist.R
import com.jccsisc.mylist.common.core.DataState
import com.jccsisc.mylist.utils.LambdasObjet.changeTitle
import com.jccsisc.mylist.utils.setColorNavBar
import com.jccsisc.mylist.utils.setOnSingleClickListener
import com.jccsisc.mylist.utils.showToast
import com.jccsisc.mylist.utils.showView
import java.util.*

fun HomeFragment.initElements() = with(mBinding) {

    activity?.let {
        it.setColorNavBar(R.color.primary_color)
    }

    changeTitle(getString(R.string.lista_de_invitados), false)

    //initRV(list)

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

                list.forEach {
                    if (it.nombre.lowercase(Locale.ROOT).contains(search)) {
                        listAux.add(it)
                    }
                }

                initRV(listAux)
                tvEmpty.showView(listAux.size == 0)
            } else {
                listAux.clear()
                listAux.addAll(list)
                initRV(listAux)
            }

            return true
        }

        override fun onQueryTextSubmit(query: String?) = true
    })
}

fun HomeFragment.initObserversHome() = with(mBinding) {
    viewModel.fetchInvitadosList().observe(viewLifecycleOwner) { result ->
        when (result) {
            is DataState.Loading -> showProgress()
            is DataState.Success -> {
                hideProgressBarCustom()
                refresh.showView()
                tvEmpty.showView(false)
                list.clear()
                list.addAll(result.data)
                initRV(list)
            }
            is DataState.Failure -> {
                hideProgressBarCustom()
                tvEmpty.showView(false)
                showToast("Ocurrió un problema: ${result.exception}")
            }
        }
    }
}