package li.doerf.es.bankaccount.repositories

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.math.BigDecimal

@RedisHash("Account")
data class Account(@Id val accountNumber: String, var balance: BigDecimal = BigDecimal.ZERO)
