package li.doerf.es.bankaccount.repositories

import java.math.BigDecimal
import java.time.Instant
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Transaction(
        @Id @GeneratedValue val id: Long? = null,
        val accountNumber: String,
        val amount: BigDecimal,
        val timestamp: Instant
)