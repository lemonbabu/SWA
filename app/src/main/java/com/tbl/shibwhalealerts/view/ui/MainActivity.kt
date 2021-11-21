package com.tbl.shibwhalealerts.view.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.tbl.shibwhalealerts.R
import com.tbl.shibwhalealerts.service.TxListService
import com.tbl.shibwhalealerts.view.ui.fragment.DashboardFragment
import com.tbl.shibwhalealerts.view.ui.fragment.DetailsFragment
import com.tbl.shibwhalealerts.viewModel.FragmentCommunication
import com.tbl.shibwhalealerts.viewModel.MainModelView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.DelicateCoroutinesApi
import java.lang.Exception

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity(), FragmentCommunication {

    private lateinit var viewModel: MainModelView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Services Calling for Txn data
        Intent(this, TxListService::class.java).also{
            startService(it)
        }


        viewModel = ViewModelProvider(this)[MainModelView::class.java]
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
            viewModel.setState(DashboardFragment())
            viewModel.setBack(false)
            viewModel.setTitle("Latest Transactions")
        }
    }

    override fun passData() {
        viewModel.setState(DetailsFragment())
        viewModel.setTitle("Transaction Details")
        viewModel.setBack(true)
    }

    // Clear local data
    private fun deleteAppData() {
        try {
            Runtime.getRuntime().exec("pm clear $packageName")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onBackPressed() {
        if(viewModel.back.value == true){
            viewModel.setState(DashboardFragment())
            viewModel.setBack(false)
            viewModel.setTitle("Latest Transactions")
            return
        }
        AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Closing Activity")
            .setMessage("Are you sure? Do you want to exit this app?")
            .setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                deleteAppData()
                super.onBackPressed() }
            .setNegativeButton("No", null)
            .show()
    }
}


