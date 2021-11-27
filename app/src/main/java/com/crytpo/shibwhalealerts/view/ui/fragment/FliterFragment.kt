package com.crytpo.shibwhalealerts.view.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.crytpo.shibwhalealerts.R
import com.crytpo.shibwhalealerts.databinding.FragmentFilterBinding
import com.crytpo.shibwhalealerts.viewModel.FragmentCommunication
import com.crytpo.shibwhalealerts.viewModel.MainModelView
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class FilterFragment : Fragment(R.layout.fragment_filter) {

    private lateinit var binding: FragmentFilterBinding
    private lateinit var fragmentCommunicator: FragmentCommunication
    private lateinit var viewModel: MainModelView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFilterBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity())[MainModelView::class.java]
        fragmentCommunicator = activity as FragmentCommunication
        getValue()

        binding.btnUpdate.setOnClickListener {
            val price = binding.etvPriceValue.text.toString().trim()
            if(price.isEmpty()){
                binding.etvPriceValue.error = "Price Value Can't Empty"
                binding.etvPriceValue.requestFocus()
                return@setOnClickListener
            }
            if(price.toFloat() >= 100000){
                Toast.makeText(context, "Price value set as = $price", Toast.LENGTH_SHORT).show()
                val sharedPreferences = activity?.getSharedPreferences("Filter", Context.MODE_PRIVATE)
                val editor = sharedPreferences?.edit()
                editor?.apply{
                    putBoolean("value", true)
                    putFloat("price", price.toFloat())
                }?.apply()

                fragmentCommunicator.passData("Dashboard")
//                viewModel.setState(FilterFragment())
//                viewModel.setTitle("Filtering")
//                viewModel.setBack(true)
            }else{
                Toast.makeText(context, "Price value is too low", Toast.LENGTH_SHORT).show()
            }

        }
    }

    @SuppressLint("SetTextI18n")
    private fun getValue() {
        val sharedPreferences: SharedPreferences? = activity?.getSharedPreferences("Filter", Context.MODE_PRIVATE)
        val remember = sharedPreferences?.getBoolean("value", false)
        if(remember == true){
            binding.tvCurrentValue.text = "Current Value = " + sharedPreferences.getFloat("price", 300000.0F).toString()
        } else
            binding.tvCurrentValue.text = "Current Value = " + 300000.00F.toString()
    }

}