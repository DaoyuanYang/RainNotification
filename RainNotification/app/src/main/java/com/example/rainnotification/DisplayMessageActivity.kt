package com.example.rainnotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        val message = intent.getStringExtra("MAINACTIVITY_MESSAGE")
        val textView = findViewById<TextView>(R.id.DomTextView).apply{
            text = message
        }
    }
}
