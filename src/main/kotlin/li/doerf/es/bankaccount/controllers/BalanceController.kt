package li.doerf.es.bankaccount.controllers

import li.doerf.es.bankaccount.dto.CreditRequest
import li.doerf.es.bankaccount.dto.DebitRequest
import li.doerf.es.bankaccount.dto.GetBalanceResponse
import li.doerf.es.bankaccount.services.AccountService
import li.doerf.es.bankaccount.streams.AmountAdded
import li.doerf.es.bankaccount.streams.AmountRemoved
import li.doerf.es.bankaccount.streams.BalanceProducer
import li.doerf.es.bankaccount.utils.getLogger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.messaging.support.MessageBuilder
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
@RequestMapping("/balance")
class BalanceController(private val producer: BalanceProducer, private val accountService: AccountService) {

    private val logger = getLogger(this::class.java)

    @PostMapping("/{accountNumber}/credit")
    fun credit(@PathVariable accountNumber: String, @RequestBody request: CreditRequest): ResponseEntity<HttpStatus> {
        logger.debug("received credit request $request")
        val msg = MessageBuilder.withPayload(AmountAdded(accountNumber, request.amount, Instant.now())).setHeader("type", "add").build()
        producer.balanceStreams.balanceOut().send(msg)
        logger.debug("msg sent with id: ${msg.headers["id"]}")
        return ResponseEntity.ok().build()
    }

    @PostMapping("/{accountNumber}/debit")
    fun debit(@PathVariable accountNumber: String, @RequestBody request: DebitRequest): ResponseEntity<HttpStatus> {
        logger.debug("received debit request $request")
        val msg = MessageBuilder.withPayload(AmountRemoved(accountNumber, request.amount, Instant.now())).setHeader("type", "remove").build()
        producer.balanceStreams.balanceOut().send(msg)
        logger.debug("msg sent with id: ${msg.headers["id"]}")
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{accountNumber}")
    fun balance(@PathVariable accountNumber: String): ResponseEntity<GetBalanceResponse> {
        logger.debug("received balance request $accountNumber")
        val balance = accountService.balance(accountNumber)
        return ResponseEntity.ok(GetBalanceResponse(accountNumber, balance))
    }

}