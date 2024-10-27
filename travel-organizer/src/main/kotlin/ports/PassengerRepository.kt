package org.example.ports

import org.example.domain.models.Passenger

interface PassengerRepository {
    fun find(documentNumber: String): Passenger
    fun save(passenger: Passenger)
    fun delete(documentNumber: String)
    fun update(passenger: Passenger)
    fun findAll(): List<Passenger>
}
