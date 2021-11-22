package com.crytpo.shibwhalealerts.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crytpo.shibwhalealerts.service.model.data.TxData

class DashboardViewModel: ViewModel() {

    // Create a LiveData with a String
    private val currentData: MutableLiveData<ArrayList<TxData>> by lazy {
        MutableLiveData<ArrayList<TxData>>()
    }

    fun setTxData(data: ArrayList<TxData>){
        currentData.value = data
        Log.d("ViewModel ", "Live Data = " + data.size)
    }

    fun getTxData(): LiveData<ArrayList<TxData>> {
        return currentData
    }

}

