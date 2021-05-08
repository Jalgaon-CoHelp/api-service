/*
 * MIT License
 *
 * Copyright (c) 2021 Jalgaon CoHelp
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
