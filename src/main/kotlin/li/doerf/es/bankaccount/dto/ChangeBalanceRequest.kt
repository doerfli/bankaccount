package li.doerf.es.bankaccount.dto

import java.math.BigDecimal
import java.time.Instant

data class CreditRequest(val amount: BigDecimal, val valueDate: Instant?)
data class DebitRequest(val amount: BigDecimal, val valueDate: Instant?)
