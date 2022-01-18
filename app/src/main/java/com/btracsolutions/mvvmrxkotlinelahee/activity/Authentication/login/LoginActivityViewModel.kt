package com.btracsolutions.mvvmrxkotlinelahee.activity.Authentication.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.btracsolutions.mvvmrxkotlinelahee.R
import com.btracsolutions.mvvmrxkotlinelahee.model.LoginResponse
import com.btracsolutions.mvvmrxkotlinelahee.networking.ApiResponse
import com.btracsolutions.mvvmrxkotlinelahee.networking.CommonResponse
import com.btracsolutions.mvvmrxkotlinelahee.networking.NoInternetException
import com.btracsolutions.mvvmrxkotlinelahee.repository.AuthRepository
import com.btracsolutions.mvvmrxkotlinelahee.utils.ApiConstants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.HttpException

class LoginActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val mutableLiveData: MutableLiveData<ApiResponse> = MutableLiveData<ApiResponse>()
  //  private var lan: String = LocaleHelper.getLanguage(application)
    private var errorMessage: String = application.getString(R.string.can_not_connect_to_server)


    fun sendLoginRequest(
        email: String,
        password: String
    ) {
        compositeDisposable.add(
            AuthRepository.requestLogin(
                email,
                password
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { mutableLiveData.setValue(ApiResponse.loading(null)) }
                .subscribe(
                    { response ->
                        Log.d("ApiTesting", "sendLoginRequest onSuccess ")
                        try {
                            val gson = Gson()
                            val collectionType = object :
                                TypeToken<CommonResponse?>() {}.type
                            val commonResponse: CommonResponse =
                                gson.fromJson(Gson().toJson(response), collectionType)
                            if (commonResponse.success) {
                                val type = object : TypeToken<LoginResponse?>() {}.type
                                val loginResponse: LoginResponse =
                                    gson.fromJson(Gson().toJson(response), type)
                                mutableLiveData.value = ApiResponse.success(
                                    loginResponse,
                                    commonResponse.message,
                                    ApiConstants.REQUEST_TYPE_LOGIN
                                )
                            } else {
                                Log.d(
                                    "ApiTesting",
                                    "sendLoginRequest onApiFailed errorCode ${commonResponse.error_code} "
                                )
                                mutableLiveData.postValue(
                                    ApiResponse.apiFailure(
                                        commonResponse,
                                        commonResponse.message,
                                        ApiConstants.REQUEST_TYPE_LOGIN
                                    )
                                )
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            Log.d(
                                "ApiTesting",
                                "sendLoginRequest exception ${e.localizedMessage} "
                            )
                            mutableLiveData.setValue(
                                ApiResponse.error(
                                    null,
                                    ApiConstants.ERROR_MESSAGE_INTERNAL_SERVER_ERROR,
                                    ApiConstants.REQUEST_TYPE_LOGIN
                                )
                            )
                        }
                    },
                    { throwable ->
                        Log.d("ApiTesting", "onError1 : $throwable")
                        sendErrorResponse(throwable)
                    }
                ))
    }

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