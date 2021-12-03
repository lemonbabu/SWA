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
    var addressTo: String = "",
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

): Serializable{

    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass){
            return false
        }
        other as TxData
        if (txHas != other.txHas){
            return false
        }
        if (value != other.value){
            return false
        }
        if (time != other.time){
            return false
        }
        return true
    }

    override fun hashCode(): Int {
        var result = blockNo.hashCode()
        result = 31 * result + time.hashCode()
        result = 31 * result + txHas.hashCode()
        result = 31 * result + nonce.hashCode()
        result = 31 * result + txBlockHas.hashCode()
        result = 31 * result + addressFrom.hashCode()
        result = 31 * result + cntAddress.hashCode()
        result = 31 * result + addressTo.hashCode()
        result = 31 * result + value.hashCode()
        result = 31 * result + tokenName.hashCode()
        result = 31 * result + tokenSymbol.hashCode()
        result = 31 * result + tokenDecimal.hashCode()
        result = 31 * result + txIndex.hashCode()
        result = 31 * result + gas.hashCode()
        result = 31 * result + gasPrice.hashCode()
        result = 31 * result + gasUsed.hashCode()
        result = 31 * result + cuGasUsed.hashCode()
        result = 31 * result + input.hashCode()
        result = 31 * result + confirmation.hashCode()
        return result
    }
}


//@SerializedName("isError")
//var isError: String = "",
//@SerializedName("txreceipt_status")
//var status: String = "",
