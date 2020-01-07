package com.example.findmeclient.service

import android.annotation.SuppressLint
import android.widget.TextView
import com.example.findmeclient.model.UserCoordinate
import com.google.gson.Gson
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import ua.naiksoftware.stomp.StompClient
import java.lang.Exception
import java.net.URI

class SocketServiceImpl(uri: URI, var textView: TextView) : SocketService,
    WebSocketClient(uri) {

    private lateinit var client: StompClient

    init {
        this.connectionLostTimeout = 10000
        this.connect()
    }

    @SuppressLint("SetTextI18n")
    override fun onOpen(handshakedata: ServerHandshake?) {
        textView.text = "Connected!!!"
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
    }

    override fun onMessage(message: String?) {
    }

    override fun onError(ex: Exception?) {
        textView.text = ex.toString()
    }

    override fun sendCoordinates(x: Double, y: Double) {
        val u = UserCoordinate("Test", x, y)
        val message = Gson().toJson(u)
        this.send(message)
    }
}