package com.example.rainnotification

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


        buttonForTest.setOnClickListener {
            val editText = findViewById<EditText>(R.id.editText)
            val message = editText.text.toString()
            val DomIntent = Intent(this, DisplayMessageActivity::class.java).apply {
                putExtra("MAINACTIVITY_MESSAGE", message)
            }
            startActivity(DomIntent)            //            Resource.main();
//            startActivity(Intent(this, Details::class.java))
//            d("Main page text panel", "Hello, ${editText2.text}")
        }

        buttonToDetails.setOnClickListener {
            val intentDet = Intent(this, weather_details::class.java).apply {
            }
            startActivity(intentDet)
        }


    }


}
