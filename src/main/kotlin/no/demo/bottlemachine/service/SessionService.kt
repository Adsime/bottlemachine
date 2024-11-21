package no.demo.bottlemachine.service

import jakarta.transaction.Transactional
import no.demo.bottlemachine.config.ReimbursementConfig
import no.demo.bottlemachine.dto.*
import no.demo.bottlemachine.repository.Session
import no.demo.bottlemachine.repository.SessionRepository
import no.demo.bottlemachine.verification.Verifier
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Service
class SessionService(
    private val sessionRepository: SessionRepository,
    private val verifier: Verifier,
    private val reimbursementConfig: ReimbursementConfig,
) {

    @Transactional
    fun addVessel(vessel: Vessel, sessionId: UUID?, stationId: String): Receipt {
        val session = session(sessionId, stationId)

        session.subtotal += when (vessel.type) {
            Type.CAN -> {
                session.cans += 1
                reimbursementConfig.can.toDouble()
            }

            Type.BOTTLE -> {
                session.bottles += 1
                reimbursementConfig.bottle.toDouble()
            }
        }

        return session.toReceipt()
    }

    @Transactional
    fun finish(sessionId: UUID?, stationId: String): Receipt {
        val session = session(sessionId, stationId, startSession = false)
        session.active = false

        return session.toReceipt()
    }

    private fun session(sessionId: UUID?, stationId: String, startSession: Boolean = true): Session {
        val session = when {
            sessionId == null && startSession -> startSession(stationId)
            sessionId != null -> sessionRepository.findById(sessionId).getOrNull()
            else -> null
        }

        verifier.verify(session)
        session!! // null er håndtert i verify. Usikker på hvorfor den ikke ser det
        verifier.verify(stationId, session)

        return session
    }

    private fun startSession(stationId: String): Session {
        return sessionRepository.save(Session(
            stationId = stationId
        ))
    }
}
