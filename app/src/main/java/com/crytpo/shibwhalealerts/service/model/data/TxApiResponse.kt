package com.crytpo.shibwhalealerts.service.model.data

import com.google.gson.annotations.SerializedName

class TxApiResponse (
    @SerializedName("result")
    var TxList: ArrayList<TxData>
    )