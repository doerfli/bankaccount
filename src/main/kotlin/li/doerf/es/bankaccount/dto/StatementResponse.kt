package li.doerf.es.bankaccount.dto

import java.math.BigDecimal
import java.time.Instant

data class StatementResponse(val accountNumber: String, val transactions: Collection<TransactionDto>)

data class TransactionDto(val amount: BigDecimal, val timestamp: Instant)
