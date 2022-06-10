package com.jccsisc.mylist.common.core

import java.lang.Exception

sealed class DataState<out T> {
    class Loading<out T>: DataState<T>()
    data class Success<out T>(val data: T): DataState<T>()
    data class Failure(val exception: Exception): DataState<Nothing>()
}
