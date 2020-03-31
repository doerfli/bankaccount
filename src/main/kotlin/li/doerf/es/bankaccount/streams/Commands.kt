package li.doerf.es.bankaccount.streams

import java.math.BigDecimal

data class AddAmount(val account: String, val amount: BigDecimal)
data class RemoveAmount(val account: String, val amount: BigDecimal)