package li.doerf.es.bankaccount.services

import li.doerf.es.bankaccount.repositories.Transaction
import li.doerf.es.bankaccount.repositories.TransactionRepository
import li.doerf.es.bankaccount.utils.getLogger
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.Instant

@Service
class StatementService(private val transactionRepository: TransactionRepository) {

    private val logger = getLogger(this::class.java)

    fun addTransaction(accountNumber: String, amount: BigDecimal, timestamp: Instant) {
        val trx = Transaction(null, accountNumber, amount, timestamp)
        transactionRepository.save(trx)
        logger.info("transaction saved for statement")
    }

    fun getTransactions(accountNumber: String): List<Transaction> {
        logger.debug("searching for last 10 transaction in account $accountNumber")
        return transactionRepository.findTop10ByAccountNumberOrderByTimestampDesc(accountNumber)
    }

}