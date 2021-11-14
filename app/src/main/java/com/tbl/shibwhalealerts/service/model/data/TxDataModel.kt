package com.tbl.shibwhalealerts.service.model.data

import com.tbl.shibwhalealerts.getDateTime

class TxDataModel(var data: TxData) {

    private var txt: String = ""

    fun getBlockNo():String{
        txt = "<font color=#3498DB>Block: </font>"
        return txt + data.blockNo
    }

    fun getTime(): String{
        txt = "<font color=#3498DB>TimeStamp: </font>"
        return txt + getDateTime(data.time).toString()
    }

    fun getTxHas(): String{
        txt = "<font color=#3498DB>Txn Hash: </font>"
        return txt + data.txHas
    }

    fun getNonce(): String{
        txt = "<font color=#3498DB>Nonce: </font>"
        return txt + data.nonce
    }

    fun getBlockHash():String{
        txt = "<font color=#3498DB>Block Hash: </font>"
        return txt + data.txBlockHas
    }

    fun getAddressFrom():String{
        txt = "<font color=#3498DB>From: </font>"
        return txt + data.addressFrom
    }

    fun getCntAddress():String{
        txt = "<font color=#3498DB>Contract Address: </font>"
        return txt + data.cntAddress
    }

    fun getAddressTo():String{
        txt = "<font color=#3498DB>To: </font>"
        return txt + data.addressTO
    }

    fun getValue():String{
        txt = "<font color=#3498DB>Value: </font>"
        return try {
            var price: Double = data.value.toDouble()
            val v: Double = price/100000000000000000
            price = String.format("%.3f",(price/100000000000000000 * 0.00005369)).toDouble()
            txt + v + "($" + price.toString() + ")"
        }catch (e:  NumberFormatException){
            txt + "0.00"
        }
    }

    fun getTokenName():String{
        txt = "<font color=#3498DB>Token Name: </font>"
        return txt + data.tokenName
    }

    fun getTokenSymbol():String{
        txt = "<font color=#3498DB>Token Symbol: </font>"
        return txt + data.tokenSymbol
    }

    fun getTokenDecimal():String{
        txt = "<font color=#3498DB>Token Decimal: </font>"
        return txt + data.tokenDecimal
    }

    fun getTxIndex():String{
        txt = "<font color=#3498DB>Transaction Index: </font>"
        return txt + data.txIndex
    }

    fun getGasLimit(): String{
        txt = "<font color=#3498DB>Gas Limit: </font>"
        return txt + data.gas
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

    fun getGasUsedByTx(): String{
        txt = "<font color=#3498DB>Gas Used by Txn: </font>"
        return txt + data.gasUsed
    }

    fun getCumGasUsed(): String{
        txt = "<font color=#3498DB>Cumulative Gas Used: </font>"
        return txt + data.cuGasUsed
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

    fun getInput(): String{
        txt = "<font color=#3498DB>Input: </font>"
        return txt + data.input
    }

    fun getCon(): String{
        txt = "<font color=#3498DB>Confirmations: </font>"
        return if(data.confirmation == "1")
            txt + "Success"
        else
            txt + "Failed"
    }











}