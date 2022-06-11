package com.jccsisc.mylist.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.jccsisc.mylist.common.core.DataState
import com.jccsisc.mylist.domain.login.LoginRepo
import kotlinx.coroutines.Dispatchers

class LoginVM(private val loginRepo: LoginRepo): ViewModel() {

    fun loginVm(emal:String, pws: String) = liveData(Dispatchers.IO) {
        emit(DataState.Loading())
        try {
            emit(DataState.Success(loginRepo.login(emal, pws)))
        } catch (e: Exception) {
            emit(DataState.Failure(e))
        }
    }
}

class LoginVMFactory(private val loginRepo: LoginRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(LoginRepo::class.java).newInstance(loginRepo)
    }
}