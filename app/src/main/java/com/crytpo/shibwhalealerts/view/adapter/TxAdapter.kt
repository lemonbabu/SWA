package com.crytpo.shibwhalealerts.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.crytpo.shibwhalealerts.R
import com.crytpo.shibwhalealerts.getDateTime
import com.crytpo.shibwhalealerts.service.model.data.TxData
import kotlin.collections.ArrayList

class TxAdapter (private var onTxClickListener: OnTxClickListener): RecyclerView.Adapter<TxAdapter.ViewHolder>(){

    private var txList: List<TxData> = ArrayList()

    fun setListItem(list: List<TxData>){
        val oldTxList = txList
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            TxDiffCallBack(
                oldTxList,
                list
            )
        )
        txList = list
        diffResult.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TxAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_list_item, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: TxAdapter.ViewHolder, position: Int) {
        val currentItem = txList[position]
        holder.tvTxHas.text = currentItem.txHas
        holder.tvTime.text = getDateTime(currentItem.time)
        holder.tvPrice.text = getPrice(currentItem.value)

        if (currentItem.addressTo == "0x95ad61b0a150d79219dcf64e1e6cc01f0b64c4ce"){
            holder.ivTxnImg.setImageResource(R.drawable.buys_icon)
        } else{
            holder.ivTxnImg.setImageResource(R.drawable.sells_icon)
        }

      //  holder.tvAddressFrom.text = currentItem.addressFrom
      //  holder.tvAddressTO.text = currentItem.addressTO

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
        var ivTxnImg: ImageView = itemView.findViewById(R.id.ivTxnImg)
       // var tvAddressFrom: TextView = itemView.findViewById(R.id.tvAddressFrom)
       // var tvAddressTO: TextView = itemView.findViewById(R.id.tvAddressTo)
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

    interface OnTxClickListener{
        fun onTxClickListener(results: TxData)
    }

    class TxDiffCallBack(
        private var oldTxList: List<TxData>,
        private var newTxList: List<TxData>
    ): DiffUtil.Callback(){
        override fun getOldListSize(): Int {
            return oldTxList.size
        }

        override fun getNewListSize(): Int {
            return newTxList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldTxList[oldItemPosition].txHas == newTxList[newItemPosition].txHas)
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean{
            return oldTxList[oldItemPosition].equals(newTxList[newItemPosition])
        }

    }
}


