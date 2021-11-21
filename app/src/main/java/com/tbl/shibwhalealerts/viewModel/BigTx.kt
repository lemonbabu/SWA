package com.tbl.shibwhalealerts.viewModel

import androidx.lifecycle.MutableLiveData
import com.tbl.shibwhalealerts.service.model.data.TxData

object BigTx {
    val txs = MutableLiveData<ArrayList<TxData>>()
}