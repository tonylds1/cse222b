package org.example.adapters

import org.example.domain.models.Trip
import org.example.ports.TripRepository
import java.util.UUID

class SQLiteTripRepository : TripRepository {
    val trips = mutableListOf<Trip>()

    override fun find(id: UUID): Trip {
        return trips.first { it.id == id }
    }

    override fun save(trip: Trip) {
        trips.add(trip)
    }

    override fun delete(id: UUID) {
        trips.removeIf { it.id == id }
    }

    override fun update(trip: Trip) {
        val index = trips.indexOfFirst { it.id == trip.id }
        trips[index] = trip
    }

    override fun findAll(): List<Trip> {
        return trips
    }
}
