package com.jccsisc.mylist.ui.fragments.home

import androidx.fragment.app.viewModels
import com.google.firebase.firestore.FirebaseFirestore
import com.jccsisc.mylist.R
import com.jccsisc.mylist.common.base.BaseFragment
import com.jccsisc.mylist.common.constants.MyConstant.DB_INVITADOS
import com.jccsisc.mylist.databinding.FragmentHomeBinding
import com.jccsisc.mylist.ui.fragments.home.adapter.HomeAdapter
import com.jccsisc.mylist.data.model.invitado.InvitadoModel
import com.jccsisc.mylist.data.remote.home.HomeDataSource
import com.jccsisc.mylist.domain.home.HomeRepoImpl
import com.jccsisc.mylist.presentation.home.HomeVM
import com.jccsisc.mylist.presentation.home.HomeVMFactory
import com.jccsisc.mylist.utils.showToast

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    //Intancia del VM
    val viewModel by viewModels<HomeVM> { HomeVMFactory(HomeRepoImpl(HomeDataSource())) }
    var adapter = HomeAdapter()
    var listInvitados = ArrayList<InvitadoModel>()

    override fun getLayout() = R.layout.fragment_home

    override fun initObservers() {
       // initObserversHome()
    }

    override fun initView() {
        initElements()
    }

    fun initRV() = with(mBinding) {
        adapter = HomeAdapter()
        rvTotalInvitados.adapter = adapter
    }

    fun configFirestorage() {
        val db = FirebaseFirestore.getInstance()

        db.collection(DB_INVITADOS)
            .get()
            .addOnSuccessListener { snapshots ->
                for (document in snapshots) {
                    val invitadoModel = document.toObject(InvitadoModel::class.java)
                    listInvitados.add(invitadoModel)
                    adapter.submitList(listInvitados)
                }
            }
            .addOnFailureListener {
                showToast("Error al consultar datos")
            }
    }

}