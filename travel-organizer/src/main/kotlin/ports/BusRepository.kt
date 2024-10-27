package org.example.ports

import org.example.domain.models.Bus
import java.util.UUID

interface BusRepository {
    fun find(id: UUID): Bus
    fun save(bus: Bus)
    fun delete(id: UUID)
    fun update(bus: Bus)
    fun findAll(): List<Bus>
}
