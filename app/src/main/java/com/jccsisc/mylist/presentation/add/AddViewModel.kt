package com.jccsisc.mylist.presentation.add

import androidx.lifecycle.*
import com.jccsisc.mylist.common.core.MyResult
import com.jccsisc.mylist.data.model.invitado.InvitadoModel
import com.jccsisc.mylist.domain.add.AddRepo
import com.jccsisc.mylist.domain.home.HomeRepo
import kotlinx.coroutines.Dispatchers

class AddViewModel(private val addRepo: AddRepo) : ViewModel() {

    private val _progreso = MutableLiveData(0)
    val progreso: LiveData<Int>
        get() = _progreso

    init {
        resetPorcentaje()
    }

    var valor = 0

    private fun resetPorcentaje() =  _progreso.value

    fun setPorcentaje(porcentaje: Int) {
        if (valor <= 90) {
            valor += porcentaje
        }
        _progreso.value = valor
    }

    fun addInviadoVM(invitadoModel: InvitadoModel) = liveData(Dispatchers.IO) {
        emit(MyResult.Loading())

        try {
            emit(MyResult.Success(addRepo.addInvidato(invitadoModel)))
        } catch (e: Exception) {
            emit(MyResult.Failure(e))
        }
    }
}
class AddVMFactory(private val addRepo: AddRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(AddRepo::class.java).newInstance(addRepo)
    }
}
