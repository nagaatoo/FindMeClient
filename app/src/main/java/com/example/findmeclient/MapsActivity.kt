package com.example.findmeclient

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.findmeclient.listener.FindLocationListener
//import com.example.findmeclient.listener.FindLocationListener
import com.example.findmeclient.service.SocketService
import com.example.findmeclient.service.SocketServiceImpl
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView
import java.net.URI

class MapsActivity : AppCompatActivity() {

    private lateinit var locationManager: LocationManager
    private lateinit var listener: LocationListener
    private lateinit var socketService: SocketService
    private lateinit var map: MapView

    private var uri = "ws://10.255.255.9:8551/radar"
    private val REQUEST_PERMISSION_LOCATION = 256

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInit()
        locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        socketService = SocketServiceImpl(URI(uri), findViewById<TextView>(R.id.textView2))
        listener = FindLocationListener(map, findViewById<TextView>(R.id.textView2), socketService)

        if (ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(Array(1){android.Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_PERMISSION_LOCATION)
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 50L, 0.01F, listener)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    private fun layoutInit() {
        val context = applicationContext
        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context))
        setContentView(R.layout.activity_maps)
        map = findViewById(R.id.mapview)
        map.setTileSource(TileSourceFactory.MAPNIK)
    }

}
