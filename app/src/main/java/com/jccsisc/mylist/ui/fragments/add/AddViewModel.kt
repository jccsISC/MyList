package com.jccsisc.mylist.ui.fragments.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddViewModel : ViewModel() {

    private val _progreso = MutableLiveData(0)
    val progreso: LiveData<Int>
        get() = _progreso

    init {
        resetPorcentaje()
    }

    private fun resetPorcentaje() =  _progreso.value

    fun setPorcentaje(porcentaje: Int) {
        _progreso.value = porcentaje
    }

}