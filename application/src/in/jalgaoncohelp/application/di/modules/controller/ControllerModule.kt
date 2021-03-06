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
package `in`.jalgaoncohelp.application.di.modules.controller

import `in`.jalgaoncohelp.api.authentication.jwt.JwtController
import `in`.jalgaoncohelp.api.hospital.HospitalController
import `in`.jalgaoncohelp.api.resource.ResourceController
import `in`.jalgaoncohelp.api.taluka.TalukaController
import `in`.jalgaoncohelp.api.user.UserController
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun DI.MainBuilder.controllerModule() {
    bind<JwtController>() with singleton { JwtController(instance()) }
    bind<TalukaController>() with singleton { TalukaController(instance()) }
    bind<UserController>() with singleton { UserController(instance(), instance(), instance(), instance()) }
    bind<HospitalController>() with singleton { HospitalController(instance()) }
    bind<ResourceController>() with singleton { ResourceController(instance()) }
}
