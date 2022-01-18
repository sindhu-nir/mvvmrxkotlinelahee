package com.btracsolutions.mvvmrxkotlinelahee.networking

import java.io.IOException

class NoInternetException : IOException() {
    override fun getLocalizedMessage(): String? {
        return "No internet connection"
    }
}