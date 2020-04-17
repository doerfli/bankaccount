package li.doerf.es.bankaccount.utils

inline fun <T : Any, R> T?.ifNotNullOrElse(ifNotNullPath: (T) -> R, elsePath: () -> R)
        = let { if(it == null) elsePath() else ifNotNullPath(it) }