package li.doerf.es.bankaccount.streams

import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Source

@EnableBinding(Source::class)
class BalanceProducer(val source: Source) {
}