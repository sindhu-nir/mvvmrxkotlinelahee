package com.btracsolutions.mvvmrxkotlinelahee.activity.Authentication.otpverify

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.btracsolutions.mvvmrxkotlinelahee.R
import com.btracsolutions.mvvmrxkotlinelahee.networking.ApiResponse
import com.btracsolutions.mvvmrxkotlinelahee.networking.NoInternetException
import com.btracsolutions.mvvmrxkotlinelahee.utils.ApiConstants
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException

class LoginVerifyViewModel (application: Application) : AndroidViewModel(application){


    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val mutableLiveData: MutableLiveData<ApiResponse> = MutableLiveData<ApiResponse>()
    //  private var lan: String = LocaleHelper.getLanguage(application)
    private var errorMessage: String = application.getString(R.string.can_not_connect_to_server)



    private fun sendErrorResponse(throwable: Throwable) {
        when (throwable) {
            is NoInternetException -> {
                Log.d("ApiTesting", "Internet not available")
                mutableLiveData.value = ApiResponse.error(
                    throwable,
                    ApiConstants.ERROR_MESSAGE_NO_INTERNET,
                    null
                )
            }
            is HttpException -> {
//                val errorMessage = CommonErrorHandler.getErrorMessage(throwable)
                Log.d("ApiTesting", "onError2 : $throwable")
                mutableLiveData.value = ApiResponse.error(
                    throwable,
                    errorMessage,
                    null
                )
            }
            else -> {
                mutableLiveData.value = ApiResponse.error(
                    throwable,
                    errorMessage,
                    null
                )
            }
        }
    }


    fun getSignInResponse(): MutableLiveData<ApiResponse> {
        return mutableLiveData
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}