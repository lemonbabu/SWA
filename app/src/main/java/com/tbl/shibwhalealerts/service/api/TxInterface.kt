package com.tbl.shibwhalealerts.service.api

import com.tbl.shibwhalealerts.service.model.data.TxApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TxInterface {
    @GET("api/")
    fun getTxs(

        @Query("module") module: String = "account",
        @Query("action") action: String = "txlist",
        @Query("address") address: String = "0x95ad61b0a150d79219dcf64e1e6cc01f0b64c4ce",
        @Query("startblock") startblock: Int = 0,
        @Query("endblock") endblock: Int = 99999999,
        @Query("page") page: Int = 1,
        @Query("offset") offset: Int = 10,
        @Query("sort") sort: String = "asc",
        @Query("apikey") apikey: String = "TJ3GUKZE4EZT9TMVNP14SW1GHRVG2ER4A5"
    ): Call<TxApiResponse>
}