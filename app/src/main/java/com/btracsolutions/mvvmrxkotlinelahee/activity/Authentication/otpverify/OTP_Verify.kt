package com.btracsolutions.mvvmrxkotlinelahee.activity.Authentication.otpverify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.btracsolutions.mvvmrxkotlinelahee.BaseActivity
import com.btracsolutions.mvvmrxkotlinelahee.R

class OTP_Verify : BaseActivity() , View.OnClickListener{

    lateinit var etOtp: AppCompatEditText
    lateinit var btnSubmit: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verify)

        initView()
        initListener()
        initViewModel()
    }

    private fun initViewModel() {
        TODO("Not yet implemented")
    }

    private fun initListener() {
        TODO("Not yet implemented")
        btnSubmit.setOnClickListener(this)
    }

    private fun initView() {
       etOtp=findViewById(R.id.etOtp)
       btnSubmit=findViewById(R.id.btnSubmit)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnSubmit->{

            }
        }
    }
}