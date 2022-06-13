package com.jccsisc.mylist.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.jccsisc.mylist.common.core.MyResult
import com.jccsisc.mylist.data.model.invitado.InvitadoModel
import com.jccsisc.mylist.domain.home.HomeRepo
import kotlinx.coroutines.Dispatchers

class HomeVM(private val homeRepo: HomeRepo) : ViewModel() {

    fun fetchInvitadosList() = liveData(Dispatchers.IO) {
        emit(MyResult.Loading())
        try {
            emit(homeRepo.getInvitadosList())
        } catch (e: Exception) {
            emit(MyResult.Failure(e))
        }
    }

    fun registerAsistencia(invitadoModel: InvitadoModel) = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(MyResult.Loading())
        kotlin.runCatching {
            homeRepo.registerAsistencia(invitadoModel)
        }.onSuccess {
            emit(MyResult.Success(Unit))
        }.onFailure { throwable ->
            emit(MyResult.Failure(Exception(throwable.message)))
        }
    }
}

/**
 * Generamos una instancia del VM
 * */
class HomeVMFactory(private val homeRepo: HomeRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HomeRepo::class.java).newInstance(homeRepo)
    }
}
