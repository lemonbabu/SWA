package com.crytpo.shibwhalealerts.service.model.data

import com.google.gson.annotations.SerializedName

class TxApiResponse (
    @SerializedName("result")
    var TxList: ArrayList<TxData>,
    @SerializedName("status")
    var status: String,
    @SerializedName("message")
    var message: String
    )