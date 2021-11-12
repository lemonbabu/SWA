package com.tbl.shibwhalealerts.viewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tbl.shibwhalealerts.view.ui.fragment.DashboardFragment

class MainModelView: ViewModel() {

    private val currentState: MutableLiveData<Fragment> by lazy {
        MutableLiveData<Fragment>()
    }

    private val title: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val back: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    init {
        currentState.value = DashboardFragment()
        title.value = "Latest Transactions"
        back.value = false
    }


    fun setTitle(data: String){
        title.value = data
    }

    fun getTitle(): LiveData<String>{
        return title
    }

    fun setBack(data: Boolean){
        back.value = data
    }

    fun getBack(): LiveData<Boolean>{
        return back
    }

    fun setState(fragment: Fragment){
        currentState.value = fragment
    }

    fun getState(): LiveData<Fragment>{
        return currentState
    }

}