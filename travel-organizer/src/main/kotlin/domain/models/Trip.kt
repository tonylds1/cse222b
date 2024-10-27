package org.example.domain.models

import java.time.LocalDateTime
import java.util.UUID

data class Trip (
    val id: UUID = UUID.randomUUID(),
    val departurePoint: String,
    val arrivalPoint: String,
    val departureDateTime: LocalDateTime,
    val arrivalDateTime: LocalDateTime,
    val bus: Bus,
    val passengers: MutableMap<Int, Passenger?> = mutableMapOf()
)

fun Trip.printTripInfo() {
    println("Trip ID: $id")
    println("Departure point: $departurePoint")
    println("Arrival point: $arrivalPoint")
    println("Departure date and time: $departureDateTime")
    println("Arrival date and time: $arrivalDateTime")
    println("Bus ID: ${bus.id}")
    println()
}

fun Trip.addPassenger(seat: Int, passenger: Passenger): Boolean {
    return if (seat < bus.capacity && isSeatAvailable(seat)) {
        passengers[seat] = passenger
        true
    } else {
        false
    }
}

fun Trip.removePassenger(passenger: Passenger): Boolean {
    val seatNumber = passengers.entries.firstOrNull { it.value == passenger }?.key
    return if (seatNumber != null) {
        passengers[seatNumber] = null
        true
    } else {
        false
    }
}

fun Trip.printPassengerList() {
    if (passengers.isEmpty()) {
        println("No passengers on board")
        return
    }

    passengers.forEach { (seatNumber, passenger) ->
        println("Seat number: $seatNumber, Passenger: $passenger")
    }
}

fun Trip.startTrip() {
    println("Trip started")

    this.printTripInfo()
    this.printPassengerList()
}

 fun Trip.isSeatAvailable(seatNumber: Int): Boolean {
    return passengers[seatNumber] == null
}
