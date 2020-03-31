package li.doerf.es.bankaccount.controllers

import li.doerf.es.bankaccount.dto.CreditRequest
import li.doerf.es.bankaccount.streams.BalanceProducer
import li.doerf.es.bankaccount.utils.getLogger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.messaging.support.MessageBuilder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/balance")
class BalanceController(private val producer: BalanceProducer) {

    private val logger = getLogger(this::class.java)

    @PostMapping("/credit")
    fun credit(@RequestBody request: CreditRequest): ResponseEntity<HttpStatus> {
        logger.debug("received credit request $request")
        producer.source.output().send(
                MessageBuilder.withPayload(request).build())
        return ResponseEntity.ok().build()
    }

}