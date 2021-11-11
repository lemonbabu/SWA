package com.tbl.shibwhalealerts.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tbl.shibwhalealerts.R
import com.tbl.shibwhalealerts.service.model.data.TxData
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TxAdapter (private val txList: ArrayList<TxData>, var onTxClickListener: OnTxClickListener): RecyclerView.Adapter<TxAdapter.ViewHolder>(){

    private val dayInMilliSec = 86400000

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TxAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_list_item, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: TxAdapter.ViewHolder, position: Int) {
        val currentItem = txList[position]
        holder.tvTxHas.text = currentItem.txHas
        holder.tvTime.text = getDateTime(currentItem.time)
        holder.tvPrice.text = getPrice(currentItem.gasPrice)
        holder.tvAddressFrom.text = currentItem.addressFrom
        holder.tvAddressTO.text = currentItem.addressTO

        holder.itemView.setOnClickListener {
            onTxClickListener.onTxClickListener(txList[position])
        }

    }

    override fun getItemCount(): Int {
        return txList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tvTxHas: TextView = itemView.findViewById(R.id.tvTxHas)
        var tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        var tvTime: TextView = itemView.findViewById(R.id.tvTime)
        var tvAddressFrom: TextView = itemView.findViewById(R.id.tvAddressFrom)
        var tvAddressTO: TextView = itemView.findViewById(R.id.tvAddressTo)


    }

    interface OnTxClickListener{
        fun onTxClickListener(results: TxData)
    }



    @SuppressLint("SimpleDateFormat")
    private fun getDateTime(s: String): String? {
        return try {
            val sdf = SimpleDateFormat("MM/dd/yyyy")
            val netDate = Date(s.toLong() * 1000 ).addDay(1)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }

    private fun Date.addDay(numberOfDaysToAdd: Int): Date{
        return Date(this.time + numberOfDaysToAdd * dayInMilliSec)
    }

    private fun getPrice(s: String): String?{
        return try {
            val price: Double = s.toDouble()
            (price/1000000000).toString()
        }catch (e:  NumberFormatException){
            "0.00"
        }
    }

}

