package com.crytpo.shibwhalealerts.view.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.crytpo.shibwhalealerts.service.model.data.TxData
import com.crytpo.shibwhalealerts.view.adapter.TxAdapter
import com.crytpo.shibwhalealerts.viewModel.FragmentCommunication
import com.crytpo.shibwhalealerts.viewModel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import androidx.lifecycle.ViewModelProvider
import com.crytpo.shibwhalealerts.R
import com.crytpo.shibwhalealerts.gone
import com.crytpo.shibwhalealerts.viewModel.BigTx
import com.crytpo.shibwhalealerts.viewModel.DashboardViewModel
import com.crytpo.shibwhalealerts.visible
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class DashboardFragment : Fragment(), TxAdapter.OnTxClickListener {

    private lateinit var adapter: TxAdapter

    private lateinit var fragmentCommunicator: FragmentCommunication
    private lateinit var viewModel: ItemViewModel
    private lateinit var model: DashboardViewModel


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        model = ViewModelProvider(requireActivity())[DashboardViewModel::class.java]
        view.progressBar.visible()
        view.rvTxList.gone()
        view.rvTxList.layoutManager = GridLayoutManager(context,1)
        view.rvTxList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))


        //Service Live data by object
        BigTx.txs.observe(viewLifecycleOwner){
            model.setTxData(it)
        }

        view.swfRefresh.setOnRefreshListener {
            //model.setTxData(obj.getTopTxs())
        }

        model.getTxData().observe(viewLifecycleOwner, {
            loadData(it)
        })
        return view
    }

    // Item onclick listener
    override fun onTxClickListener(results: TxData) {
        viewModel = ViewModelProvider(requireActivity())[ItemViewModel::class.java]
        viewModel.setData(results)
        fragmentCommunicator = activity as FragmentCommunication
        fragmentCommunicator.passData()
    }


    private fun loadData(data: ArrayList<TxData>){
        adapter = TxAdapter(data, this)
        rvTxList.adapter = adapter
        progressBar.gone()
        rvTxList.visible()
        swfRefresh.isRefreshing = false
    }

}

