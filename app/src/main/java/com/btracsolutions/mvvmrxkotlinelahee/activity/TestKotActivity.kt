package com.btracsolutions.mvvmrxkotlinelahee.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.banglatrac.carcopolo.kotlin.model.GeoResponse
import com.banglatrac.carcopolo.kotlin.networking.ApiResponse
import com.banglatrac.carcopolo.kotlin.utils.NetworkConnectivityChecker
import com.btracsolutions.mvvmrxkotlinelahee.preferenes.MySharedPref
import com.btracsolutions.mvvmrxkotlinelahee.R


class TestKotActivity : AppCompatActivity() {


    private lateinit var rvGeofence: RecyclerView
    private lateinit var progressBar: ProgressBar
    lateinit var mySharedPref: MySharedPref
    lateinit var userApiHash: String

    private lateinit var andRegulationViewModel: TestKotViewModel

    companion object {
        fun getStarterIntent(context: Context): Intent {
            return Intent(context, TestKotActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_kot)

        initView()
        initViewModel()
    }

    private fun initViewModel() {

        andRegulationViewModel = ViewModelProvider(this).get(TestKotViewModel::class.java)
        andRegulationViewModel.getApiResponse().observe(this, androidx.lifecycle.Observer {
            consumeResponse(it)
        })
        fetchGeoFences()
    }

    private fun fetchGeoFences() {
        andRegulationViewModel.fetchGeofences(
                "en",
                userApiHash
        )
    }

    private fun initView() {
        rvGeofence= findViewById(R.id.list_geofence)
        progressBar= findViewById(R.id.progressBar)
        mySharedPref= MySharedPref(this)
        userApiHash= mySharedPref.getUserApiHash()!!
        Log.d("ApiTesting", "apiHash: $userApiHash")
        progressBar.visibility= View.GONE
        NetworkConnectivityChecker.initConnectionManager(this)


    }



    /*** "ApiResponse" is a common class which is used to send api response result to Activity class;
     * This method handles the api response and display the tasks accordingly;  */
    private fun consumeResponse(apiResponse: ApiResponse) {
        Log.d("ApiTesting", "consumeResponse")
        when (apiResponse.status) {
            ApiResponse.Status.LOADING -> {
                //showProgressBar()
                progressBar.visibility= View.VISIBLE
            }
            ApiResponse.Status.SUCCESS -> {
              //  hideProgressBar()
                progressBar.visibility= View.GONE

                val geoResponse = apiResponse.data as GeoResponse
                geoResponse.items?.let {
                    Log.d("ApiTesting", "consumeResponse")

                }
            }

            ApiResponse.Status.FAILED, ApiResponse.Status.ERROR -> {
                progressBar.visibility= View.GONE
                apiResponse.message?.let {
                    //notifyLongMessage(it)
                    Log.d("ApiTesting", "consumeResponse")

                }
            }
        }
    }
}