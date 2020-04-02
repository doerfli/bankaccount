package li.doerf.es.bankaccount.streams

import org.springframework.cloud.stream.annotation.Input
import org.springframework.messaging.SubscribableChannel

interface StatementStreams {

    @Input
    fun statementIn(): SubscribableChannel

}