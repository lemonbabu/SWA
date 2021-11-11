package com.tbl.shibwhalealerts.service.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TxApiModel {
    private const val URL = "https://api.etherscan.io/"

    //retrofit builder
    fun getApiClient(): TxInterface {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(TxInterface::class.java)
    }
}