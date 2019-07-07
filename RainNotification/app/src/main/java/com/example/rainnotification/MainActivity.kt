package com.example.rainnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log.d
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    val url_BASE = "https://api.openweathermap.org/data/2.5/weather?q="
    var id = ""
    val url_END = "&appid=ee114fcf17bfeb308215ed3f430824f7"

    var jsonFmtElem = arrayOf<String>("coord", "weather", "base", "main"
                    , "visibility", "wind", "clouds", "timezone", "name", "id")

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
        d("Final URL: ", finalURL)


        var jsonObjectRequest = JsonObjectRequest(Request.Method.GET, finalURL, null,
            Response.Listener { response ->
                var unformattedJson = JsonFormatter(response)
                textView.text = "Response: %s".format(formatJSON(unformattedJson))
                d("Weather info: ", "Response: %s\n".format(response.toString()))
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
                d("Error: ", "Error occurred in JsonObjectRequest!!!")
            }
        )


        val queue = Volley.newRequestQueue(this)
        queue.add(jsonObjectRequest)

    }

    fun formatJSON(jsonFormatter: JsonFormatter):String{
        var rtn = "Here is the weather: \n \n"
        for (elem in jsonFmtElem){
            rtn += elem.toUpperCase() + ":       " + jsonFormatter.oriJsonObject.get(elem) + "\n"
        }
        return rtn
    }




    // Notification
    //
// Create an explicit intent for an Activity in your app
    val myIntent = Intent(this, AlertDetails::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
    val CHANNEL_ID = ""
    val NOTIFICATION_ID = 1

    val builder = NotificationCompat.Builder(this, CHANNEL_ID)
        .setSmallIcon(R.drawable.notification_icon_background)
        .setContentTitle("My notification")
        .setContentText("Hello World!")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        // Set the intent that will fire when the user taps the notification
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)



    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "This is name"//Settings.Global.getString(R.string.channel_name)
            val descriptionText = "This is descriptionText"   // Settings.Global.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    with(NotificationManagerCompat.from(this)) {
        // notificationId is a unique int for each notification that you must define
        (NOTIFICATION_ID, builder.build())
    }


}
