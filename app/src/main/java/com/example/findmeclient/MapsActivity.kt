package com.example.findmeclient

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RemoteViewsService
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.findmeclient.listener.FindLocationListener
import com.example.findmeclient.service.ConnectionService
import com.example.findmeclient.service.ConnectionServiceImpl

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var lockManager: LocationManager
    private lateinit var listener: LocationListener
    private lateinit var connectService: ConnectionService
    private val REQUEST_PERMISSION_LOCATION = 256

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        listener = FindLocationListener(mMap, findViewById<TextView>(R.id.textView4))
        lockManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        connectService = ConnectionServiceImpl(findViewById<TextView>(R.id.textView4))

        if (ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(Array(1){android.Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_PERMISSION_LOCATION)
        } else {
            lockManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 10F, listener)
        }
        connectService.connect()
    }
}
