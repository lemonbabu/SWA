package com.crytpo.shibwhalealerts.view.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.crytpo.shibwhalealerts.R
import com.crytpo.shibwhalealerts.service.TxListService_old
import com.crytpo.shibwhalealerts.view.ui.fragment.*
import com.crytpo.shibwhalealerts.viewModel.FragmentCommunication
import com.crytpo.shibwhalealerts.viewModel.MainModelView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity(), FragmentCommunication {

    private lateinit var viewModel: MainModelView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nav = intent.getStringExtra("nav")

        // Services Calling for Txn data
        Intent(this, TxListService_old::class.java).also{
            startService(it)
        }
        viewModel = ViewModelProvider(this)[MainModelView::class.java]

        when (nav) {
            "Details" -> {
                val bundle = Bundle()
                bundle.putString("notification", "yes")
                val fragment = DetailsFragment()
                fragment.arguments = bundle

                viewModel.setState(fragment)
                viewModel.setTitle("Transaction Details")
                viewModel.setBack(true)
                menuBar.visibility = View.GONE
            }
            else -> {
                goDashboard("buys")
                tvBuys.setTextColor(Color.parseColor("#E84361"))
                tvSales.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }



        //Fragment set Observer
        viewModel.getState().observe(this, {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fcvMain, it)
            fragmentTransaction.commit()
        })

        viewModel.getTitle().observe(this, {
            tv_header_title.text = it
        })

        viewModel.getBack().observe(this, {
            if(it == false)
                btnBack.visibility = View.INVISIBLE
            else
                btnBack.visibility = View.VISIBLE
        })

        btnBack.setOnClickListener {
            goDashboard("buys")
            tvBuys.setTextColor(Color.parseColor("#E84361"))
            tvSales.setTextColor(Color.parseColor("#FFFFFF"))
        }

        btnSetting.setOnClickListener {
            viewModel.setState(FilterFragment())
            viewModel.setTitle("Setting")
            viewModel.setBack(true)
            menuBar.visibility = View.GONE
        }

        btnBuyShib.setOnClickListener{
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.coinbase.com/join/woodfi_iq"))
            startActivity(browserIntent)
        }



        tvBuys.setOnClickListener {
            goDashboard("buys")
            tvBuys.setTextColor(Color.parseColor("#E84361"))
            tvSales.setTextColor(Color.parseColor("#FFFFFF"))
        }

        tvSales.setOnClickListener {
            goDashboard("sells")
            tvBuys.setTextColor(Color.parseColor("#FFFFFF"))
            tvSales.setTextColor(Color.parseColor("#E84361"))
        }

    }

    override fun passData(data: String) {
        when (data) {
            "Filter" -> {
                viewModel.setState(FilterFragment())
                viewModel.setTitle("Setting")
                viewModel.setBack(true)
                menuBar.visibility = View.GONE
            }
            "Details" -> {
                viewModel.setState(DetailsFragment())
                viewModel.setTitle("Transaction Details")
                viewModel.setBack(true)
                menuBar.visibility = View.GONE
            }
            else -> {
                goDashboard("buys")
                tvBuys.setTextColor(Color.parseColor("#E84361"))
                tvSales.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }
    }

    // Clear local data
//    private fun deleteAppData() {
//        try {
//            Runtime.getRuntime().exec("pm clear $packageName")
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }

    override fun onBackPressed() {

        if(viewModel.back.value == true){
            goDashboard("buys")
            tvBuys.setTextColor(Color.parseColor("#E84361"))
            tvSales.setTextColor(Color.parseColor("#FFFFFF"))
            return
        }
        AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Closing Activity")
            .setMessage("Are you sure? Do you want to exit this app?")
            .setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                //deleteAppData()
                super.onBackPressed() }
            .setNegativeButton("No", null)
            .show()
    }

    private fun goDashboard(m: String){
        tvBuys.setTextColor(Color.parseColor("#E84361"))
        tvSales.setTextColor(Color.parseColor("#FFFFFF"))
        val bundle = Bundle()
        bundle.putString("menu", m)
        val fragment = DashboardFragment()
        fragment.arguments = bundle

        viewModel.setState(fragment)
        viewModel.setBack(false)
        viewModel.setTitle("Latest Transactions")
        menuBar.visibility = View.VISIBLE
    }
}


