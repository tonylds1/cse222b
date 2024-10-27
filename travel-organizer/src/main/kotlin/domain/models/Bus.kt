package org.example.domain.models

import org.example.domain.models.enums.BusSize
import java.util.UUID

data class Bus (
    val size: BusSize,
    val capacity: Int = when(size) {
            BusSize.SMALL -> 10
            BusSize.MEDIUM -> 20
            BusSize.LARGE -> 40
        },
    val id: UUID = UUID.randomUUID()
)

fun Bus.printBusInfo() {
    println("================================")
    println("Bus ID: $id")
    println("Bus size: $size")
    println("Bus capacity: $capacity")
    println("")
}
