package com.crytpo.shibwhalealerts.service.model.data

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
    @SerializedName("from")
    var addressFrom: String = "",
    @SerializedName("contractAddress")
    var cntAddress: String = "",
    @SerializedName("to")
    var addressTO: String = "",
    @SerializedName("value")
    var value: String = "",
    @SerializedName("tokenName")
    var tokenName: String = "",
    @SerializedName("tokenSymbol")
    var tokenSymbol: String = "",
    @SerializedName("tokenDecimal")
    var tokenDecimal: String = "",
    @SerializedName("transactionIndex")
    var txIndex: String = "",
    @SerializedName("gas")
    var gas: String = "",
    @SerializedName("gasPrice")
    var gasPrice: String = "",
    @SerializedName("gasUsed")
    var gasUsed: String = "",
    @SerializedName("cumulativeGasUsed")
    var cuGasUsed: String = "",
    @SerializedName("input")
    var input: String = "",
    @SerializedName("confirmations")
    var confirmation: String = "",

): Serializable


//@SerializedName("isError")
//var isError: String = "",
//@SerializedName("txreceipt_status")
//var status: String = "",
