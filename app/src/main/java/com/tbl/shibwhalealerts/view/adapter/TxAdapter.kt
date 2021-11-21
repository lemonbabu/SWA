package com.tbl.shibwhalealerts.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tbl.shibwhalealerts.R
import com.tbl.shibwhalealerts.getDateTime
import com.tbl.shibwhalealerts.service.model.data.TxData
import kotlin.collections.ArrayList

class TxAdapter (private val txList: ArrayList<TxData>, private var onTxClickListener: OnTxClickListener): RecyclerView.Adapter<TxAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TxAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_list_item, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: TxAdapter.ViewHolder, position: Int) {
        val currentItem = txList[position]
        holder.tvTxHas.text = currentItem.txHas
        holder.tvTime.text = getDateTime(currentItem.time)
        holder.tvPrice.text = getPrice(currentItem.value)
        holder.tvAddressFrom.text = currentItem.addressFrom
        holder.tvAddressTO.text = currentItem.addressTO

        holder.itemView.setOnClickListener {
            onTxClickListener.onTxClickListener(txList[position])
        }
    }

    override fun getItemCount(): Int {
        Log.d("TxAdapter ", "= " + txList.size)
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

    private fun getPrice(s: String): String?{
        return try {
            var price: Double = s.toDouble()
            price = String.format("%.3f",(price/100000000000000000 * 0.00005369)).toDouble()
            "$$price"
        }catch (e:  NumberFormatException){
            "0.00"
        }
    }

}

