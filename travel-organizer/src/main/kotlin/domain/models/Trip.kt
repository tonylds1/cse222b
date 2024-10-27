package org.example.domain.models

import java.time.LocalDateTime
import java.util.UUID

data class Trip (
    val id: UUID = UUID.randomUUID(),
    val departurePoint: String,
    val arrivalPoint: String,
    val departureDateTime: LocalDateTime,
    val arrivalDateTime: LocalDateTime,
    val bus: Bus
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
    return bus.addPassenger(seat, passenger)
}

fun Trip.removePassenger(passenger: Passenger): Boolean {
    return bus.removePassenger(passenger)
}

fun Trip.printPassengerList() {
    bus.printPassengerList()
}

fun Trip.startTrip() {
    println("Trip started")

    this.printTripInfo()
    this.printPassengerList()
}
