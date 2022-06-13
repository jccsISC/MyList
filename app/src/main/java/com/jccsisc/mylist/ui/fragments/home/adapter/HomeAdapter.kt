package com.jccsisc.mylist.ui.fragments.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jccsisc.mylist.R
import com.jccsisc.mylist.databinding.ItemInvitadosV2Binding
import com.jccsisc.mylist.data.model.invitado.InvitadoModel

class HomeAdapter : ListAdapter<InvitadoModel, HomeAdapter.HomeViewHolder>(DiffCallbackHome) {

    lateinit var onClickItem: (invitadoModel: InvitadoModel) -> Unit

    companion object DiffCallbackHome: DiffUtil.ItemCallback<InvitadoModel>() {

        override fun areItemsTheSame(oldItem: InvitadoModel, newItem: InvitadoModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: InvitadoModel, newItem: InvitadoModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val mBinding = ItemInvitadosV2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val invitadoModel = getItem(position)
        holder.bind(invitadoModel)
    }


    inner class  HomeViewHolder(private val mBinding: ItemInvitadosV2Binding): RecyclerView.ViewHolder(mBinding.root) {
        fun bind(invitadoModel: InvitadoModel) = with(mBinding) {

            invitadoModel.apply {
                //tvParentezco.text = if (parentezcp == 0) "Familiar" else "Amigo"
                //tvParentezco.setBackgroundResource(if (parentezcp == 0) R.drawable.shape_familiar else R.drawable.shape_amigo)
                //imgRole.setImageResource(if (role == 0) R.drawable.ic_p else R.drawable.ic_i)
                tvNumMesa.text = invitadoModel.numeroMesa
                cbAsistencia.isChecked = asistencia
                tvNombre.text = nombre
                tvAcompanantes.text = "Juan Francisco Rodriguez\nLuis Rodriguez\nMamá de Juan"
                tvAcompanantes2.text = "Papá de Juan\nTía de Juan\nMari esposa Juan"
            }

            cardInvitados.animation = AnimationUtils.loadAnimation(root.context, R.anim.slide_in_left)

            cardInvitados.setOnClickListener {
                if (::onClickItem.isInitialized) {
                    onClickItem(invitadoModel)
                } else {
                    Log.e("error", "onClickItem no está inicializado")
                }
            }

        }
    }
}