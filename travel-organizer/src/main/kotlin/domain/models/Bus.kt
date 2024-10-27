package org.example.domain.models

import org.example.domain.models.enums.BusSize
import org.example.domain.services.Transport
import java.util.UUID

class Bus (
    val size: BusSize,
    override val capacity: Int = when(size) {
            BusSize.SMALL -> 10
            BusSize.MEDIUM -> 20
            BusSize.LARGE -> 40
        }
) : Transport {
    var id: UUID = UUID.randomUUID()
    override val passengers: MutableMap<Int, Passenger?> = mutableMapOf()

    init {
        for (seat in 1..capacity) {
            passengers[seat] = null
        }
    }

    override fun addPassenger(seatNumber: Int, passenger: Passenger): Boolean {
       return if (seatNumber < capacity && isSeatAvailable(seatNumber)) {
           passengers[seatNumber] = passenger
           true
       } else {
           false
       }
    }

    override fun removePassenger(passenger: Passenger): Boolean {
        val seatNumber = passengers.entries.firstOrNull { it.value == passenger }?.key
        return if (seatNumber != null) {
            passengers[seatNumber] = null
            true
        } else {
            false
        }
    }

    override fun isSeatAvailable(seatNumber: Int): Boolean {
        return passengers[seatNumber] == null
    }

    fun printPassengerList() {
        if (passengers.isEmpty()) {
            println("No passengers on board")
            return
        }

        passengers.forEach { (seatNumber, passenger) ->
            println("Seat number: $seatNumber, Passenger: $passenger")
        }
    }

    fun printBusInfo() {
        println("================================")
        println("Bus ID: $id")
        println("Bus size: $size")
        println("Bus capacity: $capacity")
        printPassengerList()
        println("")
    }
}
