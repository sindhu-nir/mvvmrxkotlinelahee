package com.btracsolutions.mvvmrxkotlinelahee.networking

import androidx.annotation.Keep

@Keep
enum class Status {
    SUCCESS,
    FAILED,
    ERROR,
    LOADING
}