package com.example.findmeclient.listener

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.widget.TextView
import com.example.findmeclient.service.SocketService
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class FindLocationListener(val map: MapView, val field: TextView, val socketService: SocketService) : LocationListener {

    private var marker: Marker? = null

    override fun onLocationChanged(location: Location?) {
        if (location != null) {
            val latitude = location.latitude
            val longitude = location.longitude
            field.text = "X:$latitude Y:$longitude"
            socketService.sendCoordinates(latitude, longitude);
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
        val startMarker = Marker(map)
        startMarker.position = GeoPoint(latit, longi)
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        startMarker.title = "Вы тут"
        map.overlays.add(startMarker)
    }


}