package com.jccsisc.mylist.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.jccsisc.mylist.common.core.MyResult
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
}

/**
 * Generamos una instancia del VM
 * */
class HomeVMFactory(private val homeRepo: HomeRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HomeRepo::class.java).newInstance(homeRepo)
    }
}
