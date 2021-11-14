package com.tbl.shibwhalealerts.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tbl.shibwhalealerts.service.api.TxApiModel
import com.tbl.shibwhalealerts.service.api.TxInterface
import com.tbl.shibwhalealerts.service.model.data.TxApiResponse
import com.tbl.shibwhalealerts.service.model.data.TxData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel: ViewModel() {

    private val apiClient: TxInterface by lazy { TxApiModel.getApiClient() }

    private lateinit var newArrayList: ArrayList<TxData>

    // Create a LiveData with a String
    private val currentData: MutableLiveData<ArrayList<TxData>> by lazy {
        MutableLiveData<ArrayList<TxData>>()
    }


    fun setTxData(){
        apiClient.getTxs().enqueue(object : Callback<TxApiResponse> {
            override fun onResponse(call: Call<TxApiResponse>, response: Response<TxApiResponse>) {
                if (response.isSuccessful) {
                    currentData.value = response.body()?.TxList!!
                } else
                    localData()
            }

            override fun onFailure(call: Call<TxApiResponse>, t: Throwable) {
                localData()
                Log.d("MainActivity", t.message.toString())
            }

        })
    }

    fun getTxData(): LiveData<ArrayList<TxData>> {
        return currentData
    }

    private fun localData(){
        // RecycleView
        newArrayList = arrayListOf()
        newArrayList.add(TxData("13590252",
            "54 secs ago (Nov-10-2021 07:04:08 PM +UTC)",
            "0x1F09309KGJ5DGK9573987JK1HK9038049JKF8HS2424FSF",
            "1" ,
            "0xaf01ce827008d1a1ee157ddb06866bc7e01a9375d1bdd637f192893393c13471",
            "0",
            "0xb342F8F8F8F7FS9F7S9F8S97FS9G7D8F7S9F7S",
            "0Xf5353FSF535GDG2527DGD4224252DHD747436GDG3",
            "0.3 Ether",
            "36740",
            "0.000000194128495194 Ether (194.128495194 Gwei)",
            "0",
            "Success",
            "0xf00d4b5d000000000000000000000000036c8cec1ce8d8bbf0831d840d7f29c9e3dde6fa63000000000000000000000000c5a96db085dda36ff0be390f455315d30d6d3dc52",
            "0Xf5353FSF535GDG2527DGD4224252DHD747436GDG3",
            "36733",
            "36733",
            "13521357"))


        currentData.value = newArrayList
    }

}

