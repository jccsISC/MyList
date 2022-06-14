package com.jccsisc.mylist.ui.fragments.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.jccsisc.mylist.R
import com.jccsisc.mylist.common.base.BaseFragment
import com.jccsisc.mylist.common.core.MyResult
import com.jccsisc.mylist.data.model.invitado.InvitadoModel
import com.jccsisc.mylist.data.remote.home.HomeDataSource
import com.jccsisc.mylist.databinding.FragmentHomeBinding
import com.jccsisc.mylist.domain.home.HomeRepoImpl
import com.jccsisc.mylist.presentation.home.HomeVM
import com.jccsisc.mylist.presentation.home.HomeVMFactory
import com.jccsisc.mylist.ui.activities.login.LoginActivity
import com.jccsisc.mylist.ui.fragments.home.adapter.HomeAdapter
import com.jccsisc.mylist.utils.goToActivity
import com.jccsisc.mylist.utils.showToast

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    //Intancia del VM
    val viewModel by viewModels<HomeVM> { HomeVMFactory(HomeRepoImpl(HomeDataSource())) }
    var adapter = HomeAdapter()
    var listInvitados = mutableListOf<InvitadoModel>()
    var listAux = mutableListOf<InvitadoModel>()

    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun getLayout() = R.layout.fragment_home

    override fun initObservers() = with(mBinding) {
        initObserversHome()
    }

    override fun initView() {
        initElements()
    }

    fun initRV(list:List<InvitadoModel>) = with(mBinding) {
        adapter = HomeAdapter(listInvitados)
        rvTotalInvitados.adapter = adapter
        adapter.submitList(list.sortedBy { it.nombre })

        adapter.onClickItem = {
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
        }

        adapter.onAsistenciaClickListener = {
               viewModel.registerAsistencia(it).observe(viewLifecycleOwner) { result->
                   when (result) {
                       is MyResult.Success -> {
                           viewModel.fetchInvitadosList()
                       }
                       is MyResult.Failure -> {
                           showToast("Ocurrió un problema: ${result.exception}")
                       }
                   }
               }
        }
    }

    fun closeSesion() {
        firebaseAuth.signOut()
        requireActivity().goToActivity<LoginActivity>()
        requireActivity().finish()
    }

    override fun onPause() {
        super.onPause()
        mBinding.search.setQuery("", false)
    }
}