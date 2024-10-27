package org.example.adapters

import org.example.domain.models.Bus
import org.example.ports.BusRepository
import java.util.UUID

class SQLiteBusRepository : BusRepository {
    val buses = mutableListOf<Bus>()

    override fun find(id: UUID): Bus {
        return buses.first { it.id == id }
    }

    override fun save(bus: Bus) {
        buses.add(bus)
    }

    override fun delete(id: UUID) {
        buses.removeIf { it.id == id }
    }

    override fun update(bus: Bus) {
        val index = buses.indexOfFirst { it.id == bus.id }
        buses[index] = bus
    }

    override fun findAll(): List<Bus> {
        return buses
    }

}
