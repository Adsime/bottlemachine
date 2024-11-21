package no.demo.bottlemachine.controller

import no.demo.bottlemachine.dto.Receipt
import no.demo.bottlemachine.service.SessionService
import no.demo.bottlemachine.verification.Verifier
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sessions")
class SessionController(
    private val sessionService: SessionService,
    private val verifier: Verifier

) {

    @PostMapping("/finish")
    fun finishSession(
        @RequestHeader(name = "Session-Id") sessionId: String?,
        @RequestHeader(name = "Station-Id") stationId: String
    ): ResponseEntity<Receipt> {
        val id = verifier.verifyUUID(sessionId)
        return ResponseEntity.ok(sessionService.finish(id, stationId))
    }
}
