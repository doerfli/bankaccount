package li.doerf.es.bankaccount.streams

import java.math.BigDecimal

data class AmountAdded(val account: String, val amount: BigDecimal)
data class AmountRemoved(val account: String, val amount: BigDecimal)