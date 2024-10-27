package org.example.domain.services

import org.example.domain.models.Passenger

interface Transport {
    val capacity: Int
    val passengers: MutableMap<Int, Passenger?>

    fun addPassenger(seatNumber: Int, passenger: Passenger): Boolean
    fun removePassenger(passenger: Passenger): Boolean
    fun isSeatAvailable(seatNumber: Int): Boolean
}
