package com.jccsisc.mylist.ui.fragments.home

import androidx.navigation.fragment.findNavController
import com.jccsisc.mylist.R
import com.jccsisc.mylist.common.core.DataState
import com.jccsisc.mylist.utils.LambdasObjet.changeTitle
import com.jccsisc.mylist.utils.showToast
import com.jccsisc.mylist.utils.showView

fun HomeFragment.initElements() = with(mBinding) {

    changeTitle(getString(R.string.lista_de_invitados), false)

    initRV()

    viewModel.fetchInvitadosList().observe(viewLifecycleOwner) { result->
            when(result) {
                is DataState.Loading -> showProgress()
                is DataState.Success -> {
                    hideProgressBarCustom()
                    rvTotalInvitados.showView()
                    tvEmpty.showView(false)
                    adapter.submitList(result.data)
                }
                is DataState.Failure -> {
                    hideProgressBarCustom()
                    tvEmpty.showView(false)
                    showToast("Ocurrió un problema: ${result.exception}")
                }
            }
        }

    adapter.onClickItem = {
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
    }
}

fun HomeFragment.initObserversHome() = with(mBinding) {

}