
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
package `in`.jalgaoncohelp.application.di

import `in`.jalgaoncohelp.application.di.modules.application.applicationModule
import `in`.jalgaoncohelp.application.di.modules.controller.controllerModule
import `in`.jalgaoncohelp.application.di.modules.coroutine.coroutineModule
import `in`.jalgaoncohelp.application.di.modules.datasource.dataSourceModule
import `in`.jalgaoncohelp.application.di.modules.datasource.sqlDelightModule
import `in`.jalgaoncohelp.application.di.modules.interactors.interactorsModule
import `in`.jalgaoncohelp.application.di.modules.mail.emailConfigModule
import `in`.jalgaoncohelp.application.di.modules.repository.repositoryModule
import `in`.jalgaoncohelp.application.di.modules.service.serviceModule
import io.ktor.application.Application
import org.kodein.di.ktor.di

fun Application.initDi() = di {
    applicationModule(this@initDi)
    sqlDelightModule()
    dataSourceModule()
    emailConfigModule()
    coroutineModule()
    repositoryModule()
    serviceModule()
    interactorsModule()
    controllerModule()
}
