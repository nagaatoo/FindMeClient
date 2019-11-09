package com.example.findmeclient.service

import android.widget.TextView
import com.example.findmeclient.listener.MyWebSocketListener
import okhttp3.*
import java.io.IOException

class ConnectionServiceImpl(val field: TextView) : ConnectionService {

    private var client = OkHttpClient()

    override fun connect() {
        val request = Request.Builder()
            .url("ws://<ip_addr>:8551/radar")
            .build()

        val socket = client.newWebSocket(request, MyWebSocketListener(field))
        socket.send("test")
    }

    override fun sendCoordinates(x: Double, y: Double) {
    }

}