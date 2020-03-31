package li.doerf.es.bankaccount.streams

import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.SubscribableChannel

interface BalanceStreams {

    @Input
    fun balanceIn(): SubscribableChannel

    @Output
    fun balanceOut(): MessageChannel
}