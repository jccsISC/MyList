package com.jccsisc.mylist.ui.fragments.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jccsisc.mylist.R
import com.jccsisc.mylist.data.model.invitado.InvitadoModel
import com.jccsisc.mylist.databinding.ItemInvitadosV2Binding
import com.jccsisc.mylist.utils.setOnSingleClickListener
import java.util.*

class HomeAdapter(private val listInvitados: List<InvitadoModel>? = null) :
    ListAdapter<InvitadoModel, HomeAdapter.HomeViewHolder>(DiffCallbackHome) {

    lateinit var onClickItem: (invitadoModel: InvitadoModel) -> Unit
    lateinit var onAsistenciaClickListener: (asitenciaModel: InvitadoModel) -> Unit

    companion object DiffCallbackHome : DiffUtil.ItemCallback<InvitadoModel>() {

        override fun areItemsTheSame(oldItem: InvitadoModel, newItem: InvitadoModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: InvitadoModel, newItem: InvitadoModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val mBinding =
            ItemInvitadosV2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val invitadoModel = getItem(position)
        holder.bind(invitadoModel, position)
    }


    inner class HomeViewHolder(private val mBinding: ItemInvitadosV2Binding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(invitadoModel: InvitadoModel, position: Int) {
            mBinding.apply {
                invitadoModel.apply {
                    //tvParentezco.text = if (parentezcp == 0) "Familiar" else "Amigo"
                    //tvParentezco.setBackgroundResource(if (parentezcp == 0) R.drawable.shape_familiar else R.drawable.shape_amigo)
                    //imgRole.setImageResource(if (role == 0) R.drawable.ic_p else R.drawable.ic_i)
                    tvNumMesa.text = numeroMesa
                    cbAsistencia.isChecked = asistencia
                    tvNombre.text = nombre

                    val listAc =
                        listInvitados?.filter { it.nombre != nombre && it.numeroMesa == numeroMesa }
                    var primer = ""
                    var segundo = ""
                    listAc?.let { lista ->
                        for ((index, invitado) in lista.withIndex()) {
                            val nombre = invitado.nombre.split(" ")

                            try {
                                if (index <= 3) {
                                    primer += if (nombre.size > 4) {
                                        "${nombre[0]} ${nombre[1]} ${nombre[2]} ${nombre[3]} \n"
                                    } else {
                                        "${nombre[0]} ${nombre[1]} ${nombre[2]}\n"
                                    }
                                } else {
                                    segundo += if (nombre.size > 4) {
                                        "${nombre[0]} ${nombre[1]} ${nombre[2]} ${nombre[3]}\n"
                                    } else {
                                        "${nombre[0]} ${nombre[1]} ${nombre[2]}\n"
                                    }
                                }
                            } catch (e: Exception) {
                                if (index <= 3) {
                                    primer += "${invitadoModel.nombre}\n"
                                } else {
                                    segundo += "${invitadoModel.nombre}\n"
                                }
                            }
                            this.listAcompaniantes = listAc
                        }
                    }
                    tvAcompanantes.text = primer
                    tvAcompanantes2.text = segundo
                }



                cardInvitados.animation =
                    AnimationUtils.loadAnimation(root.context, R.anim.slide_in_left)

                cardInvitados.setOnClickListener {
                    if (::onClickItem.isInitialized) {
                        onClickItem(invitadoModel)
                    } else {
                        Log.e("error", "onClickItem no está inicializado")
                    }
                }

                cbAsistencia.setOnSingleClickListener {
                    if (::onAsistenciaClickListener.isInitialized) {
                        invitadoModel.asistencia = cbAsistencia.isChecked
                        onAsistenciaClickListener(invitadoModel)

                        if (invitadoModel.asistencia) {
                            swapeItem(position, listInvitados!!.size - 1)
                        } else {
                            swapeItem(listInvitados!!.size - 1, 0)
                        }

                    } else {
                        Log.e("error", "onClickItem no está inicializado")
                    }
                }

            }
        }
    }

    fun swapeItem(fromPosition: Int, toPosition: Int) {
        Collections.swap(listInvitados, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }
}