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
package `in`.jalgaoncohelp.application.config

import `in`.jalgaoncohelp.api.exception.AuthenticationException
import `in`.jalgaoncohelp.api.exception.OperationNotAllowedException
import `in`.jalgaoncohelp.api.mainRoute
import `in`.jalgaoncohelp.api.model.unsuccessfulResponse
import `in`.jalgaoncohelp.application.authentication.configureAuth
import `in`.jalgaoncohelp.application.di.initDi
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.routing
import io.ktor.serialization.json
import kotlinx.serialization.json.Json

fun Application.installDI() {
    initDi()
}

fun Application.installCORS() {
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)

        allowSameOrigin = true
        allowCredentials = true
        allowNonSimpleContentTypes = true
        host("https://jalgaoncohelp.in")
    }
}

fun Application.installAuthentication() {
    configureAuth()
}

fun Application.installContentNegotiation() {
    install(ContentNegotiation) {
        json(
            json = Json {
                prettyPrint = true
            },
            contentType = ContentType.Application.Json
        )
    }
}

fun Application.installStatusPage() {
    install(StatusPages) {
        exception<AuthenticationException> {
            call.respond(HttpStatusCode.Unauthorized, unsuccessfulResponse(it.message))
        }
        exception<OperationNotAllowedException> {
            call.respond(HttpStatusCode.Forbidden, unsuccessfulResponse(it.message))
        }
        exception<IllegalStateException> {
            call.respond(HttpStatusCode.BadRequest, unsuccessfulResponse(it.message))
        }
        exception<IllegalArgumentException> {
            call.respond(HttpStatusCode.BadRequest, unsuccessfulResponse(it.toString()))
        }
    }
}

fun Application.installRoutes() {
    routing { mainRoute() }
}
