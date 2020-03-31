package li.doerf.es.bankaccount.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository : JpaRepository<Account, Int> {
    fun findByAccountNumber(accountNumber: String): Optional<Account>
}