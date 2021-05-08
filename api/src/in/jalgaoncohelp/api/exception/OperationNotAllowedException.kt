package `in`.jalgaoncohelp.api.exception

class OperationNotAllowedException : Exception("Not allowed to perform this operation")

fun notAllowedError(): Nothing = throw OperationNotAllowedException()