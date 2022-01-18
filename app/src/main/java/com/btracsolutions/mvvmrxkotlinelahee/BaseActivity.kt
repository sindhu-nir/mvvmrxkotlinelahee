package com.btracsolutions.mvvmrxkotlinelahee

import android.content.Context
import android.os.Build
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.btracsolutions.mvvmrxkotlinelahee.utils.ApiConstants

open class BaseActivity : AppCompatActivity() {

    open fun initSplashScreenStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    fun notifyLongMessage(message: String) {
        when (message) {
            ApiConstants.ERROR_MESSAGE_NO_INTERNET -> {
                Toast.makeText(this, getString(R.string.internet_not_available), Toast.LENGTH_LONG)
                    .show()
            }
            ApiConstants.ERROR_MESSAGE_INTERNAL_SERVER_ERROR -> {
                Toast.makeText(
                    this,
                    getString(R.string.can_not_connect_to_server),
                    Toast.LENGTH_LONG
                )
                    .show()
            }
            ApiConstants.ERROR_MESSAGE_INTERNAL_SERVER_ERROR -> {
                Toast.makeText(
                    this,
                    getString(R.string.can_not_connect_to_server),
                    Toast.LENGTH_LONG
                ).show()
            }
            else -> {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
//                finish()
            }
        }
    }

}