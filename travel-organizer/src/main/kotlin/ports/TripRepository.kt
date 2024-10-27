package org.example.ports

import org.example.domain.models.Trip
import java.util.UUID

interface TripRepository {
    fun find(id: UUID): Trip
    fun save(trip: Trip)
    fun delete(id: UUID)
    fun update(trip: Trip)
    fun findAll(): List<Trip>
}
