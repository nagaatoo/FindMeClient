package com.example.findmeclient.service

import android.widget.TextView
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient

class ConnectionServiceImpl(val field: TextView) : ConnectionService {

//    private var client = OkHttpClient()
    private lateinit var client: StompClient

    override fun connect() {
//        val request = Request.Builder()
//            .url("ws://10.255.255.9:8551/radar")
//            .build()
//
//        val socket = client.newWebSocket(request, MyWebSocketListener(field))
//        socket.send("test")

        client = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://10.255.255.9:8551/radar")
        client.connect()
//        client.topic("/topic/greetings")
    }

    override fun sendCoordinates(x: Double, y: Double) {
    }

}