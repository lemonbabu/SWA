package com.crytpo.shibwhalealerts.service.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TxApiModel {
    private const val URL = "https://api.etherscan.io/"

    //retrofit builder
    fun getBuyTxn(): ApiInterface.TxBuyInterface {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiInterface.TxBuyInterface::class.java)
    }


    // This is for Normal txn API calling system
    fun getSellTxn(): ApiInterface.TxSellInterface {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiInterface.TxSellInterface::class.java)
    }
}



