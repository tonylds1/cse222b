package org.example.application

import org.example.adapters.SQLiteBusRepository
import org.example.adapters.SQLitePassengerRepository
import org.example.adapters.SQLiteReceipt
import org.example.adapters.SQLiteTripRepository
import org.example.domain.models.*
import org.example.domain.models.enums.BusSize
import org.example.domain.models.enums.DocumentType
import org.example.domain.models.enums.PaymentStatus
import org.example.ports.BusRepository
import org.example.ports.PassengerRepository
import org.example.ports.ReceiptRepository
import org.example.ports.TripRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TravelOrganizerApplication {
    fun createBusesList() {
        val bus01 = Bus(BusSize.SMALL)
        val bus02 = Bus(BusSize.MEDIUM)
        val bus03 = Bus(BusSize.LARGE)

        busRepository.save(bus01)
        busRepository.save(bus02)
        busRepository.save(bus03)

        val buses = busRepository.findAll()

        println("Bus 01 is available.")
        buses.first().printBusInfo()
    }

    fun scheduleTrip() {
        val bus = busRepository.findAll().first()

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

        val trip = Trip(
            bus = bus,
            departurePoint = "Utah Chapppel",
            departureDateTime =LocalDateTime.parse("2024-11-20 06:00:00", formatter),
            arrivalPoint = "Idahoo Temple",
            arrivalDateTime = LocalDateTime.parse("2024-11-20 18:00:00", formatter)
        )

        tripRepository.save(trip)

        println("Trip scheduled.")
    }

    fun addPassengersToTrip() {
        val passenger01 = Passenger("001", DocumentType.PASSPORT, "Tony Moraes")
        val passenger02 = Passenger("002", DocumentType.PASSPORT, "Maria Silva")
        val passenger03 = Passenger("003", DocumentType.PASSPORT, "João Santos")
        val passenger04 = Passenger("004", DocumentType.PASSPORT, "Ana Souza")

        passengerRepository.save(passenger01)
        passengerRepository.save(passenger02)
        passengerRepository.save(passenger03)
        passengerRepository.save(passenger04)

        val trip = tripRepository.findAll().first()
        trip.addPassenger(1, passenger01)
        trip.addPassenger(2, passenger02)
        trip.addPassenger(3, passenger03)
        trip.addPassenger(4, passenger04)

        val receipt01 = Receipt(
            passenger = passenger01,
            trip = trip,
            status = PaymentStatus.PAID,
        )

        val receipt02 = Receipt(
            passenger = passenger02,
            trip = trip,
            status = PaymentStatus.PAID,
        )

        val receipt03 = Receipt(
            passenger = passenger03,
            trip = trip,
            status = PaymentStatus.UNPAID,
        )

        val receipt04 = Receipt(
            passenger = passenger04,
            trip = trip,
            status = PaymentStatus.UNPAID,
        )

        receiptRepository.save(receipt01);
        receiptRepository.save(receipt02);
        receiptRepository.save(receipt03);
        receiptRepository.save(receipt04);

        tripRepository.update(trip)

        println("Passengers added to trip.")
    }

    fun removeUnpaidPassengers() {
        val trip = tripRepository.findAll().first()

        val passengerPaymentStatus = receiptRepository.listPassengerPaymentStatus(trip.id)
        passengerPaymentStatus.forEach { (passenger, status) ->
            if (status == PaymentStatus.UNPAID) {
                trip.removePassenger(passenger)
            }
        }

        tripRepository.update(trip)
        println("Passengers with unpaid receipts were removed from the trip.")
    }

    fun startTrip() {
        val trip = tripRepository.findAll().first()
        trip.startTrip()
    }

    private val passengerRepository: PassengerRepository = SQLitePassengerRepository()
    private val busRepository: BusRepository = SQLiteBusRepository()
    private val receiptRepository: ReceiptRepository = SQLiteReceipt()
    private val tripRepository: TripRepository = SQLiteTripRepository()

    init {
        println("")
        println("*********************************")
        println("Welcome to Travel Organizer!")
        println("*********************************")
        println("")
    }

    fun finish() {
        println("")
        println("*********************************")
        println("Thank you for using Travel Organizer!")
        println("*********************************")
        println("")
    }
}
