package com.example.rainnotification

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_about_me.*

class Details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_about_me)

        home.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            d("Dom", "Hello, ${editText.text}")
        }
    }
}