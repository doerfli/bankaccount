package li.doerf.es.bankaccount.dto

import java.math.BigDecimal

data class GetBalanceResponse(val accountNumber: String, val balance: BigDecimal)