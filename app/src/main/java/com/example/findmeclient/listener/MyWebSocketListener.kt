package com.example.findmeclient.listener

import android.widget.TextView
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class MyWebSocketListener(val field: TextView) : WebSocketListener() {

    override fun onOpen(webSocket: WebSocket, response: Response) {
        field.text = response.body()?.string()
        super.onOpen(webSocket, response)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        field.text = t.toString()
        super.onFailure(webSocket, t, response)
    }
}