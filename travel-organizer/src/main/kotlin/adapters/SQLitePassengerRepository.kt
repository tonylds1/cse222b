package org.example.adapters

import org.example.domain.models.Passenger
import org.example.ports.PassengerRepository
import java.util.UUID

class SQLitePassengerRepository : PassengerRepository{
    val passengers = mutableListOf<Passenger>()

    override fun find(documentNumber: String): Passenger {
        return passengers.first { it.documentNumber == documentNumber }
    }

    override fun save(passenger: Passenger) {
        passengers.add(passenger)
    }

    override fun delete(documentNumber: String) {
        passengers.removeIf { it.documentNumber == documentNumber }
    }

    override fun update(passenger: Passenger) {
        val index = passengers.indexOfFirst { it.documentNumber == passenger.documentNumber }
        passengers[index] = passenger
    }

    override fun findAll(): List<Passenger> {
        return passengers
    }
}
