package `in`.jalgaoncohelp.api.authentication

class JwtSpec(
    val secret: String,
    val issuer: String,
    val audience: String
)