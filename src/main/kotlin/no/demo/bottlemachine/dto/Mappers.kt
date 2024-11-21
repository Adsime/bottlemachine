package no.demo.bottlemachine.dto

import no.demo.bottlemachine.repository.Session

fun Session.toReceipt(): Receipt = Receipt(
    sessionId = id,
    cans = cans,
    bottles = bottles,
    subtotal = subtotal
)
