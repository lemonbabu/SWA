package com.crytpo.shibwhalealerts.viewModel

import androidx.lifecycle.MutableLiveData
import com.crytpo.shibwhalealerts.service.model.data.TxData

object BigTx {
    var txs = MutableLiveData<ArrayList<TxData>>()
}

object DataByNot{
    var data = MutableLiveData<TxData>()
}