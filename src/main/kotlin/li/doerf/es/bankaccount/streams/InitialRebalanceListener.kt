package li.doerf.es.bankaccount.streams

import li.doerf.es.bankaccount.utils.getLogger
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.common.TopicPartition
import org.springframework.cloud.stream.binder.kafka.KafkaBindingRebalanceListener
import org.springframework.stereotype.Component


@Component
class InitialRebalanceListener : KafkaBindingRebalanceListener {

    private val logger = getLogger(this::class.java)

    override fun onPartitionsAssigned(bindingName: String?, consumer: Consumer<*, *>?, partitions: MutableCollection<TopicPartition>?, initial: Boolean) {
        super.onPartitionsAssigned(bindingName, consumer, partitions, initial)
        logger.debug("$bindingName $initial $partitions")
        if (initial)
            for (partition in partitions!!)
                consumer?.seek(partition, 0)
    }

}