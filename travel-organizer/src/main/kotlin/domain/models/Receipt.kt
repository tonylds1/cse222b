package org.example.domain.models

import org.example.domain.models.enums.PaymentStatus
import java.time.LocalDateTime
import java.util.UUID

data class Receipt(
    val id: UUID = UUID.randomUUID(),
    val passenger: Passenger,
    val trip: Trip,
    val status: PaymentStatus,
    val date: LocalDateTime = LocalDateTime.now()
)

fun Receipt.isPaid(): Boolean {
    return status == PaymentStatus.PAID
}
