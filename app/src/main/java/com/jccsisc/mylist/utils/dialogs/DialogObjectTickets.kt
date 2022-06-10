package com.jccsisc.mylist.utils.dialogs

import android.app.Activity
import android.app.AlertDialog
import com.jccsisc.mylist.R
import com.jccsisc.mylist.databinding.DialogGlobalBinding

object DialogObject {

    fun showDialogObjet(
        context: Activity,
        title: String? = "",
        message: String? = "",
        isCancelable: Boolean = false,
        btnAcept: () -> Unit = {}
    ) {

        val dialogView = context.layoutInflater.inflate(R.layout.dialog_global, null, false)

        val diaglog: AlertDialog = AlertDialog.Builder(context, R.style.CustomDialogTickets)
            .setView(dialogView)
            .create()

        diaglog.setCanceledOnTouchOutside(isCancelable)
        diaglog.setCancelable(isCancelable)
        diaglog.show()

        val bindingDialog = DialogGlobalBinding.bind(dialogView!!)

        /**
         * Manipulacion del layout
         * */
        bindingDialog.apply {

            tvTitle.text = title
            tvDescription.text = message

            btnAccept.setOnClickListener {
                btnAcept()
                diaglog.dismiss()
            }
        }
    }
}