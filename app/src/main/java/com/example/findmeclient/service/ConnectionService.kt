package com.example.findmeclient.service

interface ConnectionService {
    fun connect()
    fun sendCoordinates(x: Double, y: Double)
}