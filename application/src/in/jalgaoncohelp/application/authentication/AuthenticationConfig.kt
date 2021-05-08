package `in`.jalgaoncohelp.application.authentication

import `in`.jalgaoncohelp.api.authentication.jwt.JwtController
import `in`.jalgaoncohelp.api.authentication.principal.UserPrincipal
import `in`.jalgaoncohelp.core.user.UserService
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.jwt
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Application.configureAuth() {
    install(Authentication) {
        jwt {
            val jwtController by closestDI().instance<JwtController>()
            verifier(jwtController.verifier)

            validate { credential ->
                val userService by closestDI().instance<UserService>()
                val email = credential.payload.getClaim(JwtController.ClAIM).asString()

                try {
                    val user = userService.findUserByEmail(email)
                    UserPrincipal(user)
                } catch (e: Exception) {
                    null
                }
            }
        }
    }
}