package no.demo.bottlemachine.verification

import no.demo.bottlemachine.config.VesselConfig
import no.demo.bottlemachine.dto.Type
import no.demo.bottlemachine.dto.Vessel
import no.demo.bottlemachine.exception.VerificationException
import no.demo.bottlemachine.repository.Session
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class Verifier(val vesselConfig: VesselConfig) {

    fun verify(vessel: Vessel) {
        val errors = mutableListOf<String>()
        verifyEmpty(vessel, errors)

        if (errors.isNotEmpty()) {
            throw VerificationException(
                HttpStatus.BAD_REQUEST,
                errors,
                "Error while verifying $vessel - it does not pass empty threshold of ${vesselConfig.threshold}%"
            )
        }
    }

    fun verify(session: Session?) {
        if (session == null) {
            // id ble gitt, men det finnes ikke en sesjon
            throw VerificationException(
                HttpStatus.BAD_REQUEST,
                listOf("Sesjonen finnes ikke. Vennligst start på nytt."),
                "Session does not exist"
            )
        }

        if (session.inactive()) {
            // Sesjonen finnes, men er ikke lenger aktiv
            throw VerificationException(
                HttpStatus.NOT_FOUND,
                listOf("Denne sesjonen er avsluttet. Vennligst start på nytt."),
                "Session is inactive"
            )
        }
    }

    fun verifyUUID(uuid: String?): UUID? {
        try {
            return if (uuid == null) null else UUID.fromString(uuid)
        } catch (e: IllegalArgumentException) {
            throw VerificationException(HttpStatus.BAD_REQUEST, listOf("Ugyldig referansekode."), "UUID is not valid")
        }

    }

    private fun verifyEmpty(vessel: Vessel, errors: MutableList<String>) {
        if (vessel.content > vesselConfig.threshold.toDouble()) {
            errors.add("${if (vessel.type == Type.CAN) "Boksen" else "Flasken"} må være tom.")
        }
    }

    fun verify(stationId: String, session: Session) {
        if (stationId != session.stationId) {
            throw VerificationException(HttpStatus.BAD_REQUEST, listOf("Denne sesjonen tilhører en annen panteautomat"), "Station [$stationId] does not own session [${session.id}]")
        }
    }
}
