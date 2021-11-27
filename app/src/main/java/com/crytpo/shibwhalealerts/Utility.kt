package com.crytpo.shibwhalealerts

import android.annotation.SuppressLint
import android.view.View
import com.crytpo.shibwhalealerts.service.model.data.TxData
import java.text.SimpleDateFormat
import java.util.*


//this two functions for hide and show any widget
fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

//Timestamp to date time conversion
@SuppressLint("SimpleDateFormat")
fun getDateTime(s: String): String? {
    return try {
        val date = SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(Date(s.toLong()*1000))

        // current timestamp in sec
        val epoch = System.currentTimeMillis()/1000
        // Difference between two epoc
        var dif = epoch - s.toLong()
        if(dif < 0 )
            dif = 0
        val timeDif: String
        when {
            dif<60 -> {
                timeDif = "$dif sec ago"
            }
            dif/60 < 60 -> {
                timeDif = "${dif/60} min ago"
            }
            dif/3600 < 24 -> {
                timeDif = "${dif/3600} hour ago"
            }
            dif/86400 < 360 -> {
                timeDif = "${dif/86400} day ago"
            }
            else ->{
                timeDif = "${dif/31556926} year ago"
            }
        }
        "($timeDif) $date"

    } catch (e: Exception) {
        e.toString()
    }
}

fun getPrice(data: TxData):Double{
    return try {
        var price: Double = data.value.toDouble()
        price = String.format("%.3f",(price/100000000000000000 * 0.00005369)).toDouble()
        price
    }catch (e:  NumberFormatException){
        0.00
    }
}

