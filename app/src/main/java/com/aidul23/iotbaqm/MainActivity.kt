package com.aidul23.iotbaqm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aidul23.iotbaqm.api.AirMonitorService
import com.aidul23.iotbaqm.api.RetrofitHelper
import com.aidul23.iotbaqm.repository.AirMonitorRepository
import com.aidul23.iotbaqm.viewmodels.MainViewModel
import com.aidul23.iotbaqm.viewmodels.MainViewModelFactory
import com.bumptech.glide.Glide
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //image links of slider
        val urls = arrayOf(
            "https://st2.depositphotos.com/1001633/7907/v/600/depositphotos_79077060-stock-illustration-environmental-pollution-and-environment-protection.jpg",
            "https://us.123rf.com/450wm/lightwise/lightwise1903/lightwise190300027/119265536-concept-of-pollution-and-toxic-pollutants-inside-the-human-body-and-eating-contaminated-food-as-an-o.jpg?ver=6",
            "https://wallpapercave.com/wp/wp2446902.jpg"
        )

        val currentTime = Calendar.getInstance()[Calendar.HOUR_OF_DAY]

        val textViewGreeting: TextView = findViewById(R.id.textView_greeting)

        if (currentTime >= 0 && currentTime <= 5) {
            textViewGreeting.setText("Good Night")
        } else if (currentTime >= 6 && currentTime <= 10) {
            textViewGreeting.setText("Good Morning")
        } else if (currentTime >= 11 && currentTime <= 15) {
            textViewGreeting.setText("Good Noon")
        } else if (currentTime >= 16 && currentTime <= 18) {
            textViewGreeting.setText("Good Afternoon")
        } else if (currentTime >= 19 && currentTime <= 20) {
            textViewGreeting.setText("Good Evening")
        } else if (currentTime >= 21 && currentTime <= 24) {
            textViewGreeting.setText("Good Night")
        }

        //image slider in home page


        //image slider in home page
        val carouselView: CarouselView = findViewById(R.id.imageSlider)
        carouselView.setPageCount(3)
        carouselView.setImageListener(ImageListener { position, imageView ->
            Glide.with(imageView).load(urls[position]).into(imageView)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        })

        val airMonitorService = RetrofitHelper.getInstance().create(AirMonitorService::class.java)
        val repository = AirMonitorRepository(airMonitorService)

        val airQuality: TextView = findViewById(R.id.airQuality)
        val carbonMonoxide: TextView = findViewById(R.id.carbonMonoxide)
        val lpg: TextView = findViewById(R.id.lpg)
        val airTemperature: TextView = findViewById(R.id.airTemperature)
        val airPressure: TextView = findViewById(R.id.airPressure)
        val altitude: TextView = findViewById(R.id.altitude)

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.airMonitor.observe(this, Observer {
            for (result in it.feeds) {
                Log.d(TAG, "onCreate: {${result.field1}}\n")
                airQuality.text = result.field1 + " ppm"
                carbonMonoxide.text = result.field2 + " ppm"
                lpg.text = result.field3 + " ppm"
                airTemperature.text = result.field4 + " °C"
                airPressure.text = result.field5 + " hPa"
                altitude.text = result.field6 + " m"
            }
        })

    }
}