package li.doerf.es.bankaccount.streams

import org.springframework.cloud.stream.annotation.EnableBinding

@EnableBinding(BalanceStreams::class)
class BalanceProducer(val balanceStreams: BalanceStreams) {
}