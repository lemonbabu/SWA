package com.tbl.shibwhalealerts.view.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.tbl.shibwhalealerts.service.model.data.TxData
import com.tbl.shibwhalealerts.view.adapter.TxAdapter
import com.tbl.shibwhalealerts.viewModel.FragmentCommunication
import com.tbl.shibwhalealerts.viewModel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import androidx.lifecycle.ViewModelProvider
import com.tbl.shibwhalealerts.R
import com.tbl.shibwhalealerts.gone
import com.tbl.shibwhalealerts.service.TxListService
import com.tbl.shibwhalealerts.viewModel.DashboardViewModel
import com.tbl.shibwhalealerts.visible
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : Fragment(), TxAdapter.OnTxClickListener {

    private lateinit var adapter: TxAdapter

    private lateinit var fragmentCommunicator: FragmentCommunication
    private lateinit var viewModel: ItemViewModel
    private lateinit var model: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val obj = TxListService()
        model = ViewModelProvider(requireActivity())[DashboardViewModel::class.java]
        view.progressBar.visible()
        view.rvTxList.gone()
        model.setTxData(obj.apiData())
        view.swfRefresh.setOnRefreshListener { model.setTxData(obj.apiData()) }

        model.getTxData().observe(viewLifecycleOwner, {
            adapter = TxAdapter(it, this)
            rvTxList.layoutManager = GridLayoutManager(context,1)
            rvTxList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            rvTxList.adapter = adapter
            progressBar.gone()
            rvTxList.visible()
            swfRefresh.isRefreshing = false
        })


//        adapter = TxAdapter(newArrayList, this)
//
//        view.rvTxList.layoutManager = GridLayoutManager(inflater.context,1)
//        view.rvTxList.addItemDecoration(DividerItemDecoration(inflater.context, DividerItemDecoration.VERTICAL))
//        view.rvTxList.adapter = adapter

        return view
    }

    // Item onclick listener
    override fun onTxClickListener(results: TxData) {
        viewModel = ViewModelProvider(requireActivity())[ItemViewModel::class.java]
        viewModel.setData(results)
        fragmentCommunicator = activity as FragmentCommunication
        fragmentCommunicator.passData()
    }

}
