package li.doerf.es.bankaccount.repositories

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Account(
        @Id @GeneratedValue val id: Long? = null,
        val accountNumber: String,
        var balance: BigDecimal = BigDecimal.ZERO)
