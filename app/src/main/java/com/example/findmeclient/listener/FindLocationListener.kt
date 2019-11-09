package com.example.findmeclient.listener

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class FindLocationListener(val map: GoogleMap, val field: TextView) : LocationListener {

    private var marker: Marker? = null

    override fun onLocationChanged(location: Location?) {
        if (location != null) {
            val latitude = location.latitude
            val longitude = location.longitude
            field.text = "X:$latitude Y:$longitude"
            markPoint(latitude, longitude)
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }

    private fun markPoint(latit: Double, longi: Double) {
        val me = LatLng(latit, longi)
        if (marker == null) {
            marker = map.addMarker(MarkerOptions().position(me).title("Me"))
        } else {
            marker!!.setPosition(me)
            marker!!.setTitle("Test")
        }
        map.moveCamera(CameraUpdateFactory.newLatLng(me))
    }


}