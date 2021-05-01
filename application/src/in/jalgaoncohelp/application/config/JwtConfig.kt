package `in`.jalgaoncohelp.application.config

import io.ktor.config.ApplicationConfig
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
class JwtConfig(config: ApplicationConfig) {
    val secret = config.property("secretKey").getString()
    val issuer = config.property("issuer").getString()
    val audience = config.property("audience").getString()
}