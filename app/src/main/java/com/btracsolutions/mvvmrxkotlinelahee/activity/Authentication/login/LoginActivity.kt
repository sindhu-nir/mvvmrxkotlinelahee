package com.btracsolutions.mvvmrxkotlinelahee.activity.Authentication.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import com.btracsolutions.mvvmrxkotlinelahee.BaseActivity
import com.btracsolutions.mvvmrxkotlinelahee.R
import com.btracsolutions.mvvmrxkotlinelahee.model.LoginResponse
import com.google.android.material.textfield.TextInputLayout
import com.btracsolutions.mvvmrxkotlinelahee.networking.ApiResponse
import com.btracsolutions.mvvmrxkotlinelahee.networking.CommonResponse
import com.btracsolutions.mvvmrxkotlinelahee.networking.Status
import com.btracsolutions.mvvmrxkotlinelahee.preference.PrefKey
import com.btracsolutions.mvvmrxkotlinelahee.preferenes.MySharedPref
import com.btracsolutions.mvvmrxkotlinelahee.utils.ApiConstants

class LoginActivity : BaseActivity(), View.OnClickListener {


    lateinit var etrLoginPhone: EditText
    lateinit var etrLoginPassword: EditText
    lateinit var progressBar: ProgressBar
    lateinit var tilLoginPhone: TextInputLayout
    lateinit var tilLoginPassword: TextInputLayout
    lateinit var btnLogin: Button
    private lateinit var viewModel: LoginActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        initView()
        initListeners()
        initViewModel()
    }
    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)
        viewModel.getSignInResponse().observe(this, androidx.lifecycle.Observer {
            consumeResponse(it)
        })

    }
    private fun initView() {
        etrLoginPhone = findViewById(R.id.etrLoginPhone)
        etrLoginPassword = findViewById(R.id.etrLoginPassword)

        tilLoginPhone = findViewById(R.id.tilLoginPhone)
        tilLoginPassword = findViewById(R.id.tilLoginPassword)
        btnLogin = findViewById(R.id.btnLogin)
        progressBar = findViewById(R.id.progressBar)
    }

    private fun initListeners() {
        btnLogin.setOnClickListener(this)

    }

    private fun signInNow(userName: String, password: String) {
        viewModel.sendLoginRequest(userName, password)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnLogin -> {
                signInNow(etrLoginPhone.text.toString(),etrLoginPassword.text.toString())
            }
//            R.id.ivGoogleSignIn -> {
//            }
        }
    }



    /*** "ApiResponse" is a common class which is used to send api response result to Activity class;
     * This method handles the api response and display the tasks accordingly;  */
    private fun consumeResponse(apiResponse: ApiResponse) {
        Log.d("ApiTesting", "consumeResponse 121")
        when (apiResponse.status) {
            Status.LOADING -> {
                showProgressBar()
            }
            Status.SUCCESS -> {
                hideProgressBar()

                if (apiResponse.requestType!!.equals(ApiConstants.REQUEST_TYPE_LOGIN)) {
                  try {
                        val successResponse = apiResponse.data as LoginResponse
                        MySharedPref.setBoolean(PrefKey.IS_LOGGEDIN, true)
                        MySharedPref.setString(PrefKey.USER_ID, successResponse.data.userId.toString())
                    } catch (e: Exception) {
                        System.out.println(e.message)
                    }


                }
            }
            Status.FAILED -> {
                hideProgressBar()
                if (apiResponse.requestType!!.equals(ApiConstants.REQUEST_TYPE_LOGIN)) {
                    val successResponse = apiResponse.data as LoginResponse
                    Log.d(
                        "ApiTesting",
                        "consumeResponse ${successResponse.message} errorCode: ${successResponse.errorCode}"
                    )
                   // handleErrorResponse(commonResponse)
                }
            }
            Status.ERROR -> {
                hideProgressBar()
                apiResponse.message?.let {
                    notifyLongMessage(it)
                }
            }
        }
    }

    private fun hideProgressBar() {
        progressBar.visibility= View.GONE
    }

    private fun showProgressBar() {
        progressBar.visibility= View.VISIBLE
    }
}