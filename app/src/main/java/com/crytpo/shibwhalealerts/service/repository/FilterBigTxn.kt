package com.crytpo.shibwhalealerts.service.repository


import com.crytpo.shibwhalealerts.getPrice
import com.crytpo.shibwhalealerts.service.model.data.TxData
import kotlin.collections.ArrayList

class FilterBigTxn {

    private var txnData : ArrayList<TxData> = ArrayList()
    private var filterPrice : Float = 300000.0f

    fun setTxnData(price: Float, data: ArrayList<TxData>){
        txnData = data
        filterPrice = price
    }
    // For filtering Big sell Txn data
    fun getBigBuyTxn(): ArrayList<TxData>{
        val bigData : ArrayList<TxData> = ArrayList()
        var index = 0
        bigData.reverse()
        for (i in txnData) {
            val price = getPrice(i)
            if (price >= filterPrice && (bigData.find {actor -> i == actor } == null) && i.addressTo == "0x95ad61b0a150d79219dcf64e1e6cc01f0b64c4ce") {
                bigData.add(0, i)
                //callNot(i)
                index++
                if (index >= 11) {
                    bigData.removeAt(10)
                    index--
                }
            }
        }
        return bigData
    }


    // For filtering Big sell Txn data
    fun getBigSellTxn(): ArrayList<TxData>{
        val bigData : ArrayList<TxData> = ArrayList()
        var index = 0
        bigData.reverse()
        for (i in txnData) {
            val price = getPrice(i)
            if (price >= filterPrice && (bigData.find {actor -> i == actor } == null) && i.cntAddress == "0x95ad61b0a150d79219dcf64e1e6cc01f0b64c4ce") {
                bigData.add(0, i)
                //callNot(i)
                index++
                if (index >= 11) {
                    bigData.removeAt(10)
                    index--
                }
            }
        }
        return bigData
    }
}