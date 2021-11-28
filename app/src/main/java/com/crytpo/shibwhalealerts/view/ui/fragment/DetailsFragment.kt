package com.crytpo.shibwhalealerts.view.ui.fragment

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.crytpo.shibwhalealerts.R
import com.crytpo.shibwhalealerts.viewModel.ItemViewModel
import com.crytpo.shibwhalealerts.databinding.FragmentDetailsBinding
import com.crytpo.shibwhalealerts.service.model.data.TxData
import com.crytpo.shibwhalealerts.service.model.data.TxDataModel
import com.crytpo.shibwhalealerts.viewModel.DataByNot
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var viewModel: ItemViewModel
    private lateinit var binding: FragmentDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
        val message = arguments?.getString("notification")

        if(message == "yes"){
            //Service Live data by object
            DataByNot.data.observe(viewLifecycleOwner){
                viewModel.setData(it)
            }
        }

        viewModel = ViewModelProvider(requireActivity())[ItemViewModel::class.java]
        viewModel.getSelectItem().observe(viewLifecycleOwner,
            Observer<TxData?> { txData ->

                val obj = TxDataModel(txData)

                binding.tvTxHas.text = Html.fromHtml(obj.getTxHas())
                binding.tvStatus.text = Html.fromHtml(obj.getCon())
                binding.tvBlockNo.text = Html.fromHtml(obj.getBlockNo())
                binding.tvTimeStamp.text = Html.fromHtml(obj.getTime())
                binding.tvFrom.text = Html.fromHtml(obj.getAddressFrom())
                binding.tvTo.text = Html.fromHtml(obj.getAddressTo())
                binding.tvValue.text = Html.fromHtml(obj.getValue())
                binding.tvContactAddress.text = Html.fromHtml(obj.getCntAddress())
                binding.tvBlockHas.text = Html.fromHtml(obj.getBlockHash())
                binding.tvGasPrice.text = Html.fromHtml(obj.getGasPrice())
                binding.tvGasLimit.text = Html.fromHtml(obj.getGasLimit())
                binding.tvGasUsed.text = Html.fromHtml(obj.getGasUsedByTx())
                binding.tvCumGasUsed.text = Html.fromHtml(obj.getCumGasUsed())
                binding.tvTxFee.text = Html.fromHtml(obj.getTxFee())
                binding.tvTxnIndex.text = Html.fromHtml(obj.getTxIndex())
                binding.tvTokenName.text = Html.fromHtml(obj.getTokenName())
                binding.tvTokenSyb.text = Html.fromHtml(obj.getTokenSymbol())
                binding.tvTokenDec.text = Html.fromHtml(obj.getTokenDecimal())
                binding.tvNonce.text = Html.fromHtml(obj.getNonce())
                binding.tvInputData.text = Html.fromHtml(obj.getInput())

            })
    }

}