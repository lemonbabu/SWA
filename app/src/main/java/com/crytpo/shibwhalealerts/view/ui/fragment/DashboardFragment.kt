package com.crytpo.shibwhalealerts.view.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.crytpo.shibwhalealerts.service.model.data.TxData
import com.crytpo.shibwhalealerts.view.adapter.TxAdapter
import com.crytpo.shibwhalealerts.viewModel.FragmentCommunication
import com.crytpo.shibwhalealerts.viewModel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import androidx.lifecycle.ViewModelProvider
import com.crytpo.shibwhalealerts.R
import com.crytpo.shibwhalealerts.databinding.FragmentDashboardBinding
import com.crytpo.shibwhalealerts.gone
import com.crytpo.shibwhalealerts.viewModel.BigTx
import com.crytpo.shibwhalealerts.viewModel.DashboardViewModel
import com.crytpo.shibwhalealerts.visible
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class DashboardFragment : Fragment(R.layout.fragment_dashboard), TxAdapter.OnTxClickListener {

    private lateinit var adapter: TxAdapter
    private lateinit var binding: FragmentDashboardBinding

    private lateinit var fragmentCommunicator: FragmentCommunication
    private lateinit var viewModel: ItemViewModel
    private lateinit var model: DashboardViewModel
    private var data = ArrayList<TxData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)

        model = ViewModelProvider(requireActivity())[DashboardViewModel::class.java]

        when (arguments?.getString("menu")) {
            "buys" -> {
                //Service Live data by object
                BigTx.txsBuy.observe(viewLifecycleOwner){
                    data = it
                    model.setTxData(it)
                    Log.d("Live Data ", it.toString())
                }
            }
            "sells" -> {
                //Service Live data by object
                BigTx.txsSale.observe(viewLifecycleOwner){
                    data = it
                    model.setTxData(it)
                    Log.d("Live Data ", it.toString())
                }
            }
            else -> {
                //Service Live data by object
                BigTx.txs.observe(viewLifecycleOwner){
                    data = it
                    model.setTxData(it)
                    Log.d("Live Data ", it.toString())
                }
            }
        }



        binding.progressBar.visible()
        binding.rvTxList.gone()
        binding.rvTxList.layoutManager = GridLayoutManager(context,1)
        binding.rvTxList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        adapter = TxAdapter( this)




        view.swfRefresh.setOnRefreshListener {
            loadData(data as List<TxData>)
        }

        model.getTxData().observe(viewLifecycleOwner, {
            loadData(it as List<TxData>)
        })


    }


    // Item onclick listener
    override fun onTxClickListener(results: TxData) {
        viewModel = ViewModelProvider(requireActivity())[ItemViewModel::class.java]
        viewModel.setData(results)
        fragmentCommunicator = activity as FragmentCommunication
        fragmentCommunicator.passData("Details")
    }


    private fun loadData(data: List<TxData>){
        adapter.setListItem(data)
        binding.rvTxList.adapter = adapter
        binding.progressBar.gone()
        binding.rvTxList.visible()
        binding.swfRefresh.isRefreshing = false
    }

}

