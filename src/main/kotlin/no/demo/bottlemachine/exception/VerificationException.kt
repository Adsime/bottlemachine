package no.demo.bottlemachine.exception

import org.springframework.http.HttpStatus

class VerificationException(
    val code: HttpStatus,
    val errors: List<String>,
    override val message: String?
) : RuntimeException(message)
