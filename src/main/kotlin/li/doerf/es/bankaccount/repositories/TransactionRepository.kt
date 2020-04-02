package li.doerf.es.bankaccount.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<Transaction, Long> {

    fun findTop10ByAccountNumberOrderByTimestampDesc(accountNumber: String): List<Transaction>

}