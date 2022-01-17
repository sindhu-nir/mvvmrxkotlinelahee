package com.btracsolutions.mvvmrxkotlinelahee.activity

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.banglatrac.carcopolo.kotlin.model.GeoResponse
import com.banglatrac.carcopolo.kotlin.networking.ApiResponse
import com.banglatrac.carcopolo.kotlin.networking.CommonResponse
import com.banglatrac.carcopolo.kotlin.networking.NoInternetException
import com.banglatrac.carcopolo.kotlin.repository.TestKotRepository
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class TestKotViewModel(application: Application) : AndroidViewModel(application) {



    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val mutableLiveData: MutableLiveData<ApiResponse> = MutableLiveData<ApiResponse>()
    private var errorMessage: String = "Cant connect to server"
    //private var lan: String = LocaleHelper.getLanguage(application)

    fun fetchGeofences( lang: String, userApiHash: String) {
        compositeDisposable.add( TestKotRepository.requestGetGeofence("en", userApiHash )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { mutableLiveData.setValue(ApiResponse.loading(null)) }

                       .subscribe(
                                {   response ->
                                    Log.d("ApiTesting", "CountryRegulationsResponse onSuccess ")
                                    try {
                                        val gson = Gson()
                                        val commonResponse: CommonResponse =
                                                gson.fromJson(gson.toJson(response), CommonResponse::class.java)

                                        Log.d(
                                                "ApiTesting",
                                                "CountryRegulationsResponse onSuccess errorCode: ${commonResponse.error_code}"
                                        )
                                        if (commonResponse.success) {
                                            val countryRegulationsResponse: GeoResponse =
                                                    gson.fromJson(
                                                            gson.toJson(response),
                                                            GeoResponse::class.java
                                                    )
                                            mutableLiveData.value = ApiResponse.success(
                                                    countryRegulationsResponse,
                                                    commonResponse.message,
                                                    "1"
                                            )
                                        } else {
                                            val countryRegulationsResponse: GeoResponse =
                                                    gson.fromJson(
                                                            gson.toJson(response),
                                                            GeoResponse::class.java
                                                    )
                                            mutableLiveData.value = ApiResponse.success(
                                                    countryRegulationsResponse,
                                                    commonResponse.message,
                                                    "1"
                                            )
//                                            val commonResponse: CommonResponse =
//                                                    gson.fromJson(
//                                                            Gson().toJson(response),
//                                                            CommonResponse::class.java
//                                                    )
//                                            mutableLiveData.value = ApiResponse.apiFailure(
//                                                    commonResponse,
//                                                    commonResponse.message,
//                                                    "1"
//                                            )
                                        }
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                        mutableLiveData.value = ApiResponse.error(e, errorMessage, null)
                                    }
                                },
                                { throwable ->
                                    Log.d("ApiTesting", "FeesAndRegulationViewModel onError $throwable")
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
                        "No Internet",
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


    fun getApiResponse(): MutableLiveData<ApiResponse> {
        return mutableLiveData
    }

    override fun onCleared() {
        super.onCleared()
        try {
            compositeDisposable.dispose()
            compositeDisposable.clear()
        }catch (e:Exception){
            print(e.message)
        }
    }


}