package li.doerf.es.bankaccount.streams

import li.doerf.es.bankaccount.dto.CreditRequest
import li.doerf.es.bankaccount.utils.getLogger
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener

@EnableBinding(BalanceStreams::class)
class BalanceSink {

    private val logger = getLogger(this::class.java)

    @StreamListener("balanceIn")
    fun handle(request: CreditRequest) {
        logger.debug("handle: $request")
    }

}