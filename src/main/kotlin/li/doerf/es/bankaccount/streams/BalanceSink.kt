package li.doerf.es.bankaccount.streams

import li.doerf.es.bankaccount.services.AccountService
import li.doerf.es.bankaccount.utils.getLogger
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.handler.annotation.Payload

@EnableBinding(BalanceStreams::class)
class BalanceSink(val accountService: AccountService) {

    private val logger = getLogger(this::class.java)

    @StreamListener("balanceIn", condition = "headers['type']=='add'")
    fun handleCredit(@Payload request: AmountAdded) {
        logger.debug("handle credit: $request")
        accountService.credit(request.account, request.amount)
    }

    @StreamListener("balanceIn", condition = "headers['type']=='remove'")
    fun handleDebit(@Payload request: AmountRemoved) {
        logger.debug("handle debit: $request")
        accountService.debit(request.account, request.amount)
    }

}