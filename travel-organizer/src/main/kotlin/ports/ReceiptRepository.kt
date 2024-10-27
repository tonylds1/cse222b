package org.example.ports

import org.example.domain.models.Passenger
import org.example.domain.models.Receipt
import org.example.domain.models.enums.PaymentStatus
import java.util.UUID

interface ReceiptRepository {
    fun find(id: UUID): Receipt
    fun listPassengerPaymentStatus(tripId: UUID): List<Pair<Passenger, PaymentStatus>>
    fun save(receipt: Receipt)
    fun delete(id: UUID)
    fun update(receipt: Receipt)
    fun findAll(): List<Receipt>
}
