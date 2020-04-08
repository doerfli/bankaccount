package li.doerf.es.bankaccount.services

import li.doerf.es.bankaccount.repositories.Account
import li.doerf.es.bankaccount.repositories.AccountRepository
import li.doerf.es.bankaccount.utils.getLogger
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class BalanceService(private val accountRepository: AccountRepository) {

    private val logger = getLogger(this::class.java)

    fun debit(accountNumber: String, amount: BigDecimal) {
        val account = accountRepository.findByAccountNumber(accountNumber).orElseGet { Account(null, accountNumber) }
        account.balance = account.balance.minus(amount)
        accountRepository.save(account)
        logger.info("account $accountNumber: debited amount $amount - new balance ${account.balance}")
    }

    fun credit(accountNumber: String, amount: BigDecimal) {
        val account = accountRepository.findByAccountNumber(accountNumber).orElseGet { Account(null, accountNumber) }
        account.balance = account.balance.plus(amount)
        accountRepository.save(account)
        logger.info("account $accountNumber: credited amount $amount - new balance ${account.balance}")
    }

    fun balance(accountNumber: String): BigDecimal {
        val account = accountRepository.findByAccountNumber(accountNumber).orElseThrow{ throw IllegalArgumentException("account does not exist: $accountNumber") }
        return account.balance
    }

}
