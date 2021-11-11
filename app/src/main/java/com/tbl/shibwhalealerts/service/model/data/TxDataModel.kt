package com.tbl.shibwhalealerts.service.model.data

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class TxDataModel(var data: TxData) {

    private var txt: String = ""

    fun getTxHas(): String{
        txt = "<font color=#3498DB>Txn Hash: </font>"
        return txt + data.txHas
    }

    fun getStatus(): String{
        txt = "<font color=#3498DB>Status: </font>"
        return if(data.status == "1")
            txt + "Success"
        else
            txt + "Failed"
    }

    fun getBlockNo():String{
        txt = "<font color=#3498DB>Block: </font>"
        return txt + data.blockNo
    }

    @SuppressLint("SimpleDateFormat")
    fun getTime(): String{
        txt = "<font color=#3498DB>TimeStamp: </font>"
        return try {
            val sdf = SimpleDateFormat("MM/dd/yyyy")
            val netDate = Date(data.time.toLong() * 1000 ).addDay(1)
            txt + sdf.format(netDate)
        } catch (e: Exception) {
            txt + e.toString()
        }
    }

    private fun Date.addDay(numberOfDaysToAdd: Int): Date {
        return Date(this.time + numberOfDaysToAdd * 86400000)
    }

    fun getAddressFrom():String{
        txt = "<font color=#3498DB>From: </font>"
        return txt + data.addressFrom
    }

    fun getAddressTo():String{
        txt = "<font color=#3498DB>To: </font>"
        return txt + data.addressTO
    }


    fun getValue():String{
        txt = "<font color=#3498DB>Value: </font>"
        return txt + data.value
    }

    fun getGasPrice(): String {
        txt = "<font color=#3498DB>Gas Price: </font>"
        return try {
            val price: Double = data.gasPrice.toDouble()
            txt + (price/1000000000).toString()
        }catch (e:  NumberFormatException){
            txt + ""
        }
    }

    fun getGasLimit(): String{
        txt = "<font color=#3498DB>Gas Limit: </font>"
        return txt + data.gas
    }

    fun getGasUsedByTx(): String{
        txt = "<font color=#3498DB>Gas Used by Txn: </font>"
        return txt + data.gasUsed
    }

    fun getTxFee(): String{
        txt = "<font color=#3498DB>Txn Fee: </font>"
        return try {
            val txFee: Double = data.gasUsed.toDouble() * data.gasPrice.toDouble()/1000000000
            txt + txFee.toString()
        }catch (e:  NumberFormatException){
            txt + ""
        }
    }

    fun getNonce(): String{
        txt = "<font color=#3498DB>Nonce: </font>"
        return txt + data.nonce
    }

    fun getInput(): String{
        txt = "<font color=#3498DB>Input: </font>"
        return txt + data.input
    }

}