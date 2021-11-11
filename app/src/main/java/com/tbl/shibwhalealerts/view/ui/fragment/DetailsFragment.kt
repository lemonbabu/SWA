package com.tbl.shibwhalealerts.view.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tbl.shibwhalealerts.R
import com.tbl.shibwhalealerts.viewModel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details.view.tvTxHas
import androidx.lifecycle.ViewModelProviders
import com.tbl.shibwhalealerts.service.model.data.TxData


class DetailsFragment : Fragment() {

    private lateinit var viewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_details, container, false)

        return view
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity())[ItemViewModel::class.java]
        viewModel.getSelectItem().observe(viewLifecycleOwner,
            Observer<TxData?> { txData ->
                var txt = "<font color=#3498DB>Transaction Hash: </font>" + txData.txHas
                tvTxHas.text = Html.fromHtml(txt)

                txt = "<font color=#3498DB>Status: </font>" + txData.status
                tvStatus.text = Html.fromHtml(txt)

                txt = "<font color=#3498DB>Block: </font>" + txData.blockNo
                tvBlockNo.text = Html.fromHtml(txt)

                txt = "<font color=#3498DB>TimeStamp: </font>" + txData.time
                tvTimeStamp.text = Html.fromHtml(txt)

                txt = "<font color=#3498DB>From: </font>" + txData.addressFrom
                tvFrom.text = Html.fromHtml(txt)

                txt = "<font color=#3498DB>To: </font>" + txData.addressTO
                tvTo.text = Html.fromHtml(txt)

                txt = "<font color=#3498DB>Value: </font>" + txData.value
                tvValue.text = Html.fromHtml(txt)

                txt = "<font color=#3498DB>Gas Limit: </font>" + txData.gas
                tvGasLimit.text = Html.fromHtml(txt)

                txt = "<font color=#3498DB>Gas Used by Txn: </font>" + txData.gasUsed
                tvGasUsed.text = Html.fromHtml(txt)

                txt = "<font color=#3498DB>Nonce: </font>" + txData.nonce
                tvNonce.text = Html.fromHtml(txt)


            })
    }

}