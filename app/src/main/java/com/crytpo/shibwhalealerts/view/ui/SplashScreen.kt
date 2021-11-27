package com.crytpo.shibwhalealerts.view.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.crytpo.shibwhalealerts.R
import kotlinx.android.synthetic.main.activity_splash_screen.*
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private var mHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        mHandler = Handler(mainLooper)
        setPriceValueDefault()

        doWork()

    }

    private fun doWork() {
        val prg = 3000
        Thread{
            for (i in  0..100) {
                Thread.sleep(30)
                mHandler!!.post { progressBar.progress = i }
            }
        }.start()

        mHandler!!.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            intent.putExtra("nav", "Dashboard")
            finish()
        }, prg.toLong())
    }

    private fun setPriceValueDefault(){
        val sharedPreferences = getSharedPreferences("Filter", Context.MODE_PRIVATE)
        val remember = sharedPreferences.getBoolean("value", false)
        if(!remember){
            val editor = sharedPreferences.edit()
            editor.apply{
                putBoolean("value", true)
                putFloat("price", 300000F)
            }?.apply()
        }

    }
}