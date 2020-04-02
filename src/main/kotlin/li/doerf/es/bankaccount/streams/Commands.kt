package li.doerf.es.bankaccount.streams

import java.math.BigDecimal
import java.time.Instant

data class AmountAdded(val account: String, val amount: BigDecimal, val timestamp: Instant)
data class AmountRemoved(val account: String, val amount: BigDecimal, val timestamp: Instant)