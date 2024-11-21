package no.demo.bottlemachine.repository

import jakarta.persistence.*
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SessionRepository : CrudRepository<Session, UUID>

@Entity
@Table(name = "SESSION")
class Session(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,
    var cans: Int = 0,
    var bottles: Int = 0,
    var subtotal: Double = 0.0,
    var active: Boolean = true,
    val stationId: String,
) {
    fun inactive() = !active
}
