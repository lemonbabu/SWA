package com.tbl.shibwhalealerts.service.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TxData(

    @SerializedName("blockNumber")
    var blockNo: String = "",
    @SerializedName("timeStamp")
    var time: String = "",
    @SerializedName("hash")
    var txHas: String = "",
    @SerializedName("nonce")
    var nonce: String = "",
    @SerializedName("blockHash")
    var txBlockHas: String = "",
    @SerializedName("transactionIndex")
    var txIndex: String = "",
    @SerializedName("from")
    var addressFrom: String = "",
    @SerializedName("to")
    var addressTO: String = "",
    @SerializedName("value")
    var value: String = "",
    @SerializedName("gas")
    var gas: String = "",
    @SerializedName("gasPrice")
    var gasPrice: String = "",
    @SerializedName("isError")
    var isError: String = "",
    @SerializedName("txreceipt_status")
    var status: String = "",
    @SerializedName("input")
    var input: String = "",
    @SerializedName("contractAddress")
    var cntAddress: String = "",
    @SerializedName("cumulativeGasUsed")
    var cuGasUsed: String = "",
    @SerializedName("gasUsed")
    var gasUsed: String = "",
    @SerializedName("confirmations")
    var confirmation: String = "",

): Serializable
