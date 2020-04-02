package li.doerf.es.bankaccount.streams

import li.doerf.es.bankaccount.services.StatementService
import li.doerf.es.bankaccount.utils.getLogger
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.handler.annotation.Payload

@EnableBinding(StatementStreams::class)
class StatementSink(private val statementService: StatementService) {

    private val logger = getLogger(javaClass.name)

    @StreamListener("statementIn", condition = "headers['type']=='add'")
    fun handleCredit(@Payload request: AmountAdded) {
        logger.debug("handle add: $request")
        statementService.addTransaction(request.account, request.amount, request.timestamp)
    }

    @StreamListener("statementIn", condition = "headers['type']=='remove'")
    fun handleDebit(@Payload request: AmountRemoved) {
        logger.debug("handle remove: $request")
        statementService.addTransaction(request.account, -request.amount, request.timestamp)
    }

}