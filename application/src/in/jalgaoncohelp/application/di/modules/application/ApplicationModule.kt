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
package `in`.jalgaoncohelp.application.di.modules.application

import `in`.jalgaoncohelp.api.authentication.jwt.JwtSpec
import `in`.jalgaoncohelp.application.authentication.DefaultPasswordEncryptor
import `in`.jalgaoncohelp.application.config.DatabaseConfig
import `in`.jalgaoncohelp.application.config.JwtConfig
import `in`.jalgaoncohelp.core.authentication.PasswordEncryptor
import io.ktor.application.Application
import io.ktor.config.ApplicationConfig
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton

fun DI.MainBuilder.applicationModule(application: Application) {
    bindDatabaseConfig()
    bindApplicationConfig(application)
    bindJwtConfig()
    bindJwtSpec()
    bindPasswordEncrypter()
}

private fun DI.MainBuilder.bindDatabaseConfig() {
    bind<DatabaseConfig>() with singleton { DatabaseConfig(instance("database")) }
}

private fun DI.MainBuilder.bindApplicationConfig(application: Application) {
    bind<ApplicationConfig>("database") with provider { application.environment.config.config("database") }
    bind<ApplicationConfig>("jwt") with provider { application.environment.config.config("jwt") }
}

private fun DI.MainBuilder.bindPasswordEncrypter() {
    bind<PasswordEncryptor>() with singleton { DefaultPasswordEncryptor(instance<JwtConfig>().secret) }
}

private fun DI.MainBuilder.bindJwtConfig() {
    bind<JwtConfig>() with singleton { JwtConfig(instance("jwt")) }
}

private fun DI.MainBuilder.bindJwtSpec() {
    bind<JwtSpec>() with singleton { instance<JwtConfig>().let { JwtSpec(it.secret, it.issuer, it.audience) } }
}
