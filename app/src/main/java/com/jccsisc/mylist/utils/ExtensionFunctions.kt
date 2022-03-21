package com.jccsisc.mylist.utils

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

fun View.showView(showView: Boolean = true) {
    if (showView) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}


fun Fragment.showToast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.requireContext(), msg, duration).show()
}