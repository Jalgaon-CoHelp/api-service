package `in`.jalgaoncohelp.api.exception

class AuthenticationException : Exception("Authentication Failed")

fun authenticationError(): Nothing = throw AuthenticationException()