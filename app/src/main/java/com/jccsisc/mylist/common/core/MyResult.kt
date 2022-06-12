package com.jccsisc.mylist.common.core

import java.lang.Exception

sealed class MyResult<out T> {
    class Loading<out T>: MyResult<T>()
    data class Success<out T>(val data: T): MyResult<T>()
    data class Failure(val exception: Exception): MyResult<Nothing>()
}
