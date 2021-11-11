package com.tbl.shibwhalealerts.view.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.tbl.shibwhalealerts.R
import com.tbl.shibwhalealerts.service.model.data.TxData
import com.tbl.shibwhalealerts.view.ui.fragment.DashboardFragment
import com.tbl.shibwhalealerts.view.ui.fragment.DetailsFragment
import com.tbl.shibwhalealerts.viewModel.FragmentCommunication
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), FragmentCommunication {

    private var backPress: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gotoDashboard()
        btnBack.setOnClickListener { gotoDashboard() }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fcvMain, fragment)
        fragmentTransaction.commit()
    }


    @SuppressLint("SetTextI18n")
    private fun gotoDashboard(){
        tv_header_title.text = "Latest Transactions"
        replaceFragment(DashboardFragment())
        btnBack.visibility = View.INVISIBLE
        backPress = false
    }

    @SuppressLint("SetTextI18n")
    private fun gotoDetails(){
        btnBack.visibility = View.VISIBLE
        backPress = true
        tv_header_title.text = "Transaction Details"

    }



    override fun passData() {
        gotoDetails()
        replaceFragment(DetailsFragment())

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
        if(backPress){
            gotoDashboard()
            return
        }
        AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Closing Activity")
            .setMessage("Are you sure? Do you want to exit this app?")
            .setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                deleteAppData()
                super.onBackPressed() }
            .setNegativeButton("No", null)
            .show()
    }
}


