package com.example.broadcastsender

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var tvMessage: TextView
    private lateinit var buttonSendBroadcast: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvMessage = findViewById(R.id.tvMessage)
        buttonSendBroadcast = findViewById(R.id.buttonSendBroadcast)
        buttonSendBroadcast.setOnClickListener { sendBroadcast() }
    }

    private fun sendBroadcast() {
        val intent = Intent("com.example.broadcastreceivers.CUSTOM_ACTION")
        intent.putExtra("com.example.broadcastreceivers.EXTRA_TEXT", "custom broadcast received")
        sendBroadcast(intent)
    }

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contet: Context?, intent: Intent?) {
            val receiveString = intent?.getStringExtra("com.example.broadcastreceivers.EXTRA_TEXT")
            tvMessage.text = receiveString
        }
    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter("com.example.broadcastreceivers.CUSTOM_ACTION")
        registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }

}