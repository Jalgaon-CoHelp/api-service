package `in`.jalgaoncohelp.core.exception

class NotFoundException(message: String) : Exception(message)

fun notFoundError(message: String = "Not Found"): Nothing = throw NotFoundException(message)