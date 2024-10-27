package org.example.domain.models

import org.example.domain.models.enums.DocumentType

data class Passenger (
    val documentNumber: String,
    val documentType: DocumentType,
    val name: String,
)
