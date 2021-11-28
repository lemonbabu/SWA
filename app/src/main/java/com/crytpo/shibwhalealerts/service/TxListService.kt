package com.crytpo.shibwhalealerts.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.crytpo.shibwhalealerts.R
import com.crytpo.shibwhalealerts.getPrice
import com.crytpo.shibwhalealerts.service.api.TxApiModel
import com.crytpo.shibwhalealerts.service.api.TxInterface
import com.crytpo.shibwhalealerts.service.model.data.TxApiResponse
import com.crytpo.shibwhalealerts.service.model.data.TxData
import com.crytpo.shibwhalealerts.view.ui.MainActivity
import com.crytpo.shibwhalealerts.viewModel.BigTx
import com.crytpo.shibwhalealerts.viewModel.DataByNot
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

@DelicateCoroutinesApi
class TxListService : Service() {

    private var CHANNEL_ID = "Your_Channel_ID"
    private var mainHandler: Handler = Handler()
    private val apiClient: TxInterface by lazy { TxApiModel.getApiClient() }
    private var newArrayList: ArrayList<TxData> = arrayListOf()
    private var topTx: ArrayList<TxData> = arrayListOf()
    private var topTxBuy: ArrayList<TxData> = arrayListOf()
    private var topTxSale: ArrayList<TxData> = arrayListOf()
    private var index = 0
    private var indexBuy = 0
    private var indexSale = 0
    private var filterPrice by Delegates.notNull<Float>()

    override fun onBind(intent: Intent?): IBinder? = null


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("Services ", "Service Start")
        bigTx()

//        Thread{
//
//        }.start()
        return START_STICKY
    }

    private fun setValue() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("Filter", Context.MODE_PRIVATE)
        val remember = sharedPreferences.getBoolean("value", false)
        filterPrice = if(remember){
            sharedPreferences.getFloat("price", 300000.0F)
        } else
            300000.00F
        Log.d("Price Value = ", filterPrice.toString())
    }

    private fun apiData(): ArrayList<TxData> {
        apiClient.getTxs().enqueue(object : Callback<TxApiResponse> {
            override fun onResponse(call: Call<TxApiResponse>, response: Response<TxApiResponse>) {

                if(response.body()?.status == "1"){
                    newArrayList = response.body()?.TxList!!
                    Log.d("API call success= ", response.body()?.message.toString())
                }else
                    newArrayList = localData()
            }

            override fun onFailure(call: Call<TxApiResponse>, t: Throwable) {
                newArrayList = localData()
                Log.d("Internal Error", t.message.toString())
            }
        })
        return newArrayList
    }


    private fun bigTx(){
        try {
            GlobalScope.launch(Dispatchers.IO) {
                while (true) {
                    setValue()
                    val data: ArrayList<TxData> = apiData()
                    for (i in data) {
                        val price = async { getPrice(i) }
                        if (price.await() >= filterPrice && (topTx.find {actor -> i == actor } == null)) {
                            topTx.add(0, i)
                            callNot(i)
                            index++
                            if (index >= 11) {
                                topTx.removeAt(10)
                                index--
                            }
                            //This is for Sales checking
                            if(i.cntAddress == i.addressFrom){
                                topTxSale.add(0, i)
                                indexSale++
                                if (indexSale >= 11) {
                                    topTxSale.removeAt(10)
                                    indexSale--
                                }
                            }
                            //This is for Buys checking
                            if(i.cntAddress == i.addressTO){
                                topTxBuy.add(0, i)
                                indexBuy++
                                if (indexBuy >= 11) {
                                    topTxBuy.removeAt(10)
                                    indexBuy--
                                }
                            }

                            broadcastTxs(topTx, topTxBuy, topTxSale)
//                        withContext(Dispatchers.Main) {
//
//                            Log.d("Services ", "Call View model= ${topTx.size}")
//                        }
                        }
                    }
                    Log.d("Services ", "Tx data = " + data.size + " Big data = " + topTx.size)
                    delay(2000)
                }
            }
        } catch (e: Exception){
            Log.d("Services ", "No internet")
        }

    }

    private fun broadcastTxs(txs: ArrayList<TxData>, txBuy: ArrayList<TxData>, txSale: ArrayList<TxData>){
        mainHandler.post{
            BigTx.txs.value = txs
            BigTx.txsBuy.value = txBuy
            BigTx.txsSale.value = txSale
            Log.d("BigTx ", "$txs  $txBuy  $txSale")
        }
    }


//    fun getTopTxs(): ArrayList<TxData> {
//        Log.d("Services ", "Tx data = $topTx")
//        return topTx
//    }


    private fun localData(): ArrayList<TxData> {
        // RecycleView
        newArrayList = arrayListOf()
        newArrayList.add(
            TxData(
                "13590252",
                "54 secs ago (Nov-10-2021 07:04:08 PM +UTC)",
                "0x1F09309KGJ5DGK9573987JK1HK9038049JKF8HS2424FSF",
                "1",
                "0xaf01ce827008d1a1ee157ddb06866bc7e01a9375d1bdd637f192893393c13471",
                "0",
                "0xb342F8F8F8F7FS9F7S9F8S97FS9G7D8F7S9F7S",
                "0Xf5353FSF535GDG2527DGD4224252DHD747436GDG3",
                "0.3 Ether",
                "36740",
                "0.000000194128495194 Ether (194.128495194 Gwei)",
                "0",
                "Success",
                "0xf00d4b5d000000000000000000000000036c8cec1ce8d8bbf0831d840d7f29c9e3dde6fa63000000000000000000000000c5a96db085dda36ff0be390f455315d30d6d3dc52",
                "0Xf5353FSF535GDG2527DGD4224252DHD747436GDG3",
                "36733",
                "36733",
                "13521357"
            )
        )
        return newArrayList
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun callNot(data: TxData){
        createNotificationChannel()


        val intent: Intent
        val pendingIntent: PendingIntent

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            intent = Intent(this, MainActivity::class.java)
            intent.putExtra("nav", "Details")
            mainHandler.post{
                DataByNot.data.value = data
            }
            pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT
            )
        } else{
            pendingIntent = PendingIntent.getActivity(
                this,
                0,
                null,
                PendingIntent.FLAG_CANCEL_CURRENT
            )
        }

//        fragmentCommunicator = pendingIntent as FragmentCommunication
//        fragmentCommunicator.passData("Details")

        //val notificationLayout = RemoteViews(packageName, R.layout.notification_card)
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_list_tx)
            .setContentTitle("Big Transaction( $" + getPrice(data) + ")")
            .setContentText(data.txHas)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            //.setCustomBigContentView(notificationLayout)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)


       with(NotificationManagerCompat.from(this)) {
           notify(0, builder.build())
       }
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val name = "App Notification"
            val descriptionText = "This is your Notification Description"
            val importance: Int = NotificationManager.IMPORTANCE_DEFAULT
            val channel: NotificationChannel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
