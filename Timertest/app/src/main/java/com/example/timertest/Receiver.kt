package com.example.timertest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log.d
import androidx.core.content.ContextCompat.startActivity

class Receiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent : Intent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val intentToRepAct = Intent(context, Notification::class.java)
//        startActivity(intentToRepAct)
        d("Timer:   ","Timer is triggered!!!")
    }

}