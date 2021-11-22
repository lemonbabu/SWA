package com.crytpo.shibwhalealerts.view.ui.fragment

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import com.crytpo.shibwhalealerts.R
import com.crytpo.shibwhalealerts.viewModel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import androidx.lifecycle.ViewModelProviders
import com.crytpo.shibwhalealerts.service.model.data.TxData
import com.crytpo.shibwhalealerts.service.model.data.TxDataModel


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

                val obj = TxDataModel(txData)

                tvTxHas.text = Html.fromHtml(obj.getTxHas())
                tvStatus.text = Html.fromHtml(obj.getCon())
                tvBlockNo.text = Html.fromHtml(obj.getBlockNo())
                tvTimeStamp.text = Html.fromHtml(obj.getTime())
                tvFrom.text = Html.fromHtml(obj.getAddressFrom())
                tvTo.text = Html.fromHtml(obj.getAddressTo())
                tvValue.text = Html.fromHtml(obj.getValue())
                tvContactAddress.text = Html.fromHtml(obj.getCntAddress())
                tvBlockHas.text = Html.fromHtml(obj.getBlockHash())
                tvGasPrice.text = Html.fromHtml(obj.getGasPrice())
                tvGasLimit.text = Html.fromHtml(obj.getGasLimit())
                tvGasUsed.text = Html.fromHtml(obj.getGasUsedByTx())
                tvCumGasUsed.text = Html.fromHtml(obj.getCumGasUsed())
                tvTxFee.text = Html.fromHtml(obj.getTxFee())
                tvTxnIndex.text = Html.fromHtml(obj.getTxIndex())
                tvTokenName.text = Html.fromHtml(obj.getTokenName())
                tvTokenSyb.text = Html.fromHtml(obj.getTokenSymbol())
                tvTokenDec.text = Html.fromHtml(obj.getTokenDecimal())
                tvNonce.text = Html.fromHtml(obj.getNonce())
                tvInputData.text = Html.fromHtml(obj.getInput())

            })
    }

}