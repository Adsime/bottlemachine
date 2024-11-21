package no.demo.bottlemachine.controller

import no.demo.bottlemachine.dto.Receipt
import no.demo.bottlemachine.dto.Vessel
import no.demo.bottlemachine.service.SessionService
import no.demo.bottlemachine.verification.Verifier
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/vessels")
class VesselController(
    private val sessionsService: SessionService,
    private val verifier: Verifier
) {

    @PostMapping
    fun insertVessel(
        @RequestBody vessel: Vessel,
        @RequestHeader(name = "Session-Id") sessionId: String?,
        @RequestHeader(name = "Station-Id") stationId: String
    ): ResponseEntity<Receipt> {
        verifier.verify(vessel)
        val id = verifier.verifyUUID(sessionId)

        return ResponseEntity.ok(sessionsService.addVessel(vessel, id, stationId))
    }
}
