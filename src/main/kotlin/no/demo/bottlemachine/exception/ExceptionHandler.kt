package no.demo.bottlemachine.exception

import org.springframework.http.HttpStatusCode
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(value = [VerificationException::class])
    fun handleVerificationException(e: VerificationException): ResponseEntity<ProblemDetail> {
        val problemDetail = ProblemDetail.forStatus(HttpStatusCode.valueOf(e.code.value()))
            .also { it.setProperty("errors", e.errors) }

        return ResponseEntity.badRequest()
            .body(problemDetail)
    }
}
