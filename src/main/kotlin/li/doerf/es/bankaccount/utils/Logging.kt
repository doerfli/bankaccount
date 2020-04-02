package li.doerf.es.bankaccount.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun getLogger(forClass: Class<*>): Logger =
        LoggerFactory.getLogger(forClass)

fun getLogger(name: String): Logger =
        LoggerFactory.getLogger(name)
