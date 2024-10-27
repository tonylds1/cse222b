package org.example.adapters

import org.example.domain.models.Passenger
import org.example.domain.models.Receipt
import org.example.domain.models.enums.PaymentStatus
import org.example.domain.models.isPaid
import org.example.ports.ReceiptRepository
import java.util.UUID

class SQLiteReceipt : ReceiptRepository {
    val receipts = mutableListOf<Receipt>()

    override fun find(id: UUID): Receipt {
        return receipts.first { it.id == id }
    }

    override fun listPassengerPaymentStatus(tripId: UUID): List<Pair<Passenger, PaymentStatus>> {
        return receipts.filter { it.trip.id == tripId }.map { it.passenger to it.status }
    }

    override fun save(receipt: Receipt) {
        receipts.add(receipt)
    }

    override fun delete(id: UUID) {
        receipts.removeIf { it.id == id }
    }

    override fun update(receipt: Receipt) {
        val index = receipts.indexOfFirst { it.id == receipt.id }
        receipts[index] = receipt
    }

    override fun findAll(): List<Receipt> {
        return receipts
    }
}
