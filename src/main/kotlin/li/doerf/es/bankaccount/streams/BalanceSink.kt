package li.doerf.es.bankaccount.streams

import li.doerf.es.bankaccount.services.BalanceService
import li.doerf.es.bankaccount.utils.getLogger
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.handler.annotation.Payload

@EnableBinding(BalanceStreams::class)
class BalanceSink(val balanceService: BalanceService) {

    private val logger = getLogger(javaClass.name)

    @StreamListener("balanceIn", condition = "headers['type']=='add'")
    fun handleCredit(@Payload request: AmountAdded) {
        logger.debug("handle credit: $request")
        balanceService.credit(request.account, request.amount)
    }

    @StreamListener("balanceIn", condition = "headers['type']=='remove'")
    fun handleDebit(@Payload request: AmountRemoved) {
        logger.debug("handle debit: $request")
        balanceService.debit(request.account, request.amount)
    }

}