package com.example.rainnotification

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    val url_BASE = "https://api.openweathermap.org/data/2.5/weather?q="
    var id = ""
    val url_END = "&appid=ee114fcf17bfeb308215ed3f430824f7"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


        buttonSearch.setOnClickListener {
            val editText = findViewById<EditText>(R.id.editText)
            val city = editText.text.toString()
            id = city //"524901"
            textView.text = "Refreshing......"
            makeRequest()

//            val editText = findViewById<EditText>(R.id.editText)
//            val message = editText.text.toString()
//            val DomIntent = Intent(this, DisplayMessageActivity::class.java).apply {
//                putExtra("MAINACTIVITY_MESSAGE", message)
//            }
//            startActivity(DomIntent)
        }



        buttonToDetails.setOnClickListener {
            val intentDet = Intent(this, weather_details::class.java).apply {
            }
            startActivity(intentDet)
        }

        // Refresh data
        makeRequest()
        refreshButton.setOnClickListener {
            textView.text = "Refreshing......"
            makeRequest()
        }
    }

    fun makeRequest(){
        val finalURL = url_BASE + id + url_END
//        val url = "https://samples.openweathermap.org/data/2.5/forecast/daily?id=524901&appid=ee114fcf17bfeb308215ed3f430824f7"
        d("Final URL: ", finalURL)
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, finalURL, null,
            Response.Listener { response ->
                textView.text = "Response: %s".format(response.toString())
                d("Weather info: ", "Response: %s".format(response.toString()))
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
                d("Error: ", "Error occurred in JsonObjectRequest!!!")
            }
        )
        queue.add(jsonObjectRequest)

    }

}
