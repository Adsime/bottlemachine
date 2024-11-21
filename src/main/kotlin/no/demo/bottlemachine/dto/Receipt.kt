package no.demo.bottlemachine.dto

import java.util.UUID

data class Receipt(
    val sessionId: UUID?,
    val cans: Int,
    val bottles: Int,
    val subtotal: Double
)
