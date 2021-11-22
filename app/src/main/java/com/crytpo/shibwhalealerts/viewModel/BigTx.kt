package com.crytpo.shibwhalealerts.viewModel

import androidx.lifecycle.MutableLiveData
import com.crytpo.shibwhalealerts.service.model.data.TxData

object BigTx {
    val txs = MutableLiveData<ArrayList<TxData>>()
}