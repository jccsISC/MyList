package com.jccsisc.mylist.ui.fragments.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.jccsisc.mylist.R
import com.jccsisc.mylist.common.base.BaseFragment
import com.jccsisc.mylist.databinding.FragmentHomeBinding
import com.jccsisc.mylist.ui.fragments.home.adapter.HomeAdapter
import com.jccsisc.mylist.data.model.invitado.InvitadoModel
import com.jccsisc.mylist.data.remote.home.HomeDataSource
import com.jccsisc.mylist.domain.home.HomeRepoImpl
import com.jccsisc.mylist.presentation.home.HomeVM
import com.jccsisc.mylist.presentation.home.HomeVMFactory
import com.jccsisc.mylist.ui.activities.login.LoginActivity
import com.jccsisc.mylist.utils.goToActivity

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    //Intancia del VM
    val viewModel by viewModels<HomeVM> { HomeVMFactory(HomeRepoImpl(HomeDataSource())) }
    var adapter = HomeAdapter()
    var list = mutableListOf<InvitadoModel>()
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
        adapter = HomeAdapter()
        rvTotalInvitados.adapter = adapter
        adapter.submitList(list)

        adapter.onClickItem = {
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
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