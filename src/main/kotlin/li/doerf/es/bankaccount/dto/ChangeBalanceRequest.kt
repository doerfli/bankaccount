package li.doerf.es.bankaccount.dto

import java.math.BigDecimal

data class CreditRequest(val amount: BigDecimal)
data class DebitRequest(val amount: BigDecimal)
