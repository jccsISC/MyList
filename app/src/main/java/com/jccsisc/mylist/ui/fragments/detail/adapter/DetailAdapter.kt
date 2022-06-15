package com.jccsisc.mylist.ui.fragments.detail.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jccsisc.mylist.data.model.invitado.InvitadoModel
import com.jccsisc.mylist.databinding.ItemInvitadoBinding
import com.jccsisc.mylist.utils.setOnSingleClickListener

class DetailAdapter : ListAdapter<InvitadoModel, DetailAdapter.DetailViewHolder>(DiffCallbackHome) {

    lateinit var onAsistenciaClickListener: (asitenciaModel: InvitadoModel) -> Unit

    companion object DiffCallbackHome : DiffUtil.ItemCallback<InvitadoModel>() {

        override fun areItemsTheSame(oldItem: InvitadoModel, newItem: InvitadoModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: InvitadoModel, newItem: InvitadoModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val mBinding =
            ItemInvitadoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val invitadoModel = getItem(position)
        holder.bind(invitadoModel)
    }


    inner class DetailViewHolder(private val mBinding: ItemInvitadoBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(invitadoModel: InvitadoModel) = with(mBinding) {

            invitadoModel.apply {
                tvItemName.text = nombre
                cbAsistencia.isChecked = asistencia
            }

            cbAsistencia.setOnSingleClickListener {
                if (::onAsistenciaClickListener.isInitialized) {
                    invitadoModel.asistencia = cbAsistencia.isChecked
                    onAsistenciaClickListener(invitadoModel)
                } else {
                    Log.e("error", "onClickItem no está inicializado")
                }
            }

        }
    }
}