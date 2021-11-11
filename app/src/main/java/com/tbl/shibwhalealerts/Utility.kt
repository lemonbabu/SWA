package com.tbl.shibwhalealerts

import android.view.View


//this two functions for hide and show any widget
fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

// It's the country getting function from latitude and longitude in https://randomuser.me/ have no county that why need it's
//fun Context.getAddress(latitude: Double, longitude: Double): String? {
//    val result = StringBuilder()
//    try {
//        val geocoder = Geocoder(this, Locale.getDefault())
//        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
//        if (addresses.size > 0) {
//            val address = addresses[0]
//            result.append(address.countryName)
//        }
//    } catch (e: IOException) {
//        Log.e("tag", e.toString())
//    }
//    return result.toString()
//}