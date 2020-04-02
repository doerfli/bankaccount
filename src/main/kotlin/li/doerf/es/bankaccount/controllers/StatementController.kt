package li.doerf.es.bankaccount.controllers

import li.doerf.es.bankaccount.dto.StatementResponse
import li.doerf.es.bankaccount.dto.TransactionDto
import li.doerf.es.bankaccount.services.StatementService
import li.doerf.es.bankaccount.utils.getLogger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/statement")
class StatementController(private val statementService: StatementService) {

    private val logger = getLogger(this::class.java)

    @GetMapping("/{accountNumber}")
    fun statement(@PathVariable accountNumber: String): ResponseEntity<StatementResponse> {
        logger.debug("received statement request $accountNumber")
        val trxs = statementService.getTransactions(accountNumber)
        val res = StatementResponse(accountNumber, trxs.map { TransactionDto(it.amount, it.timestamp) })
        return ResponseEntity.ok(res)
    }

}