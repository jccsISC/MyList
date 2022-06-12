package com.jccsisc.mylist.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.jccsisc.mylist.common.core.MyResult
import com.jccsisc.mylist.data.model.user.UserModel
import com.jccsisc.mylist.domain.auth.AuthRepo
import kotlinx.coroutines.Dispatchers

class AuthVM(private val loginRepo: AuthRepo): ViewModel() {

    fun loginVM(emal: String, pws: String) = liveData(Dispatchers.IO) {
        emit(MyResult.Loading())
        try {
            emit(MyResult.Success(loginRepo.login(emal, pws)))
        } catch (e: Exception) {
            emit(MyResult.Failure(e))
        }
    }

    fun signInVM(emal: String, pws: String, user: UserModel) = liveData(Dispatchers.IO) {
        emit(MyResult.Loading())
        try {
            emit(MyResult.Success(loginRepo.signIn(emal, pws, user)))
        } catch (e: Exception) {
            emit(MyResult.Failure(e))
        }
    }
}

class AuthVMFactory(private val loginRepo: AuthRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(AuthRepo::class.java).newInstance(loginRepo)
    }
}