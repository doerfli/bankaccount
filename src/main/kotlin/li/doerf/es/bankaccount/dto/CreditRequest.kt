package li.doerf.es.bankaccount.dto

import java.math.BigDecimal


data class CreditRequest(val account: String, val amount: BigDecimal)
