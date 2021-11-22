package com.crytpo.shibwhalealerts.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crytpo.shibwhalealerts.service.model.data.TxData


class ItemViewModel: ViewModel() {


    // Create a LiveData with a String
    private val currentData: MutableLiveData<TxData> by lazy {
        MutableLiveData<TxData>()
    }

    fun setData(data: TxData){
        currentData.value = data
    }

    fun getSelectItem(): LiveData<TxData>{
        return currentData
    }


}

