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
package `in`.jalgaoncohelp.api.authentication.handler

import `in`.jalgaoncohelp.api.authentication.UserPermissions
import `in`.jalgaoncohelp.api.authentication.hasPermission
import `in`.jalgaoncohelp.api.authentication.principal.UserPrincipal
import `in`.jalgaoncohelp.api.exception.authenticationError
import `in`.jalgaoncohelp.api.exception.notAllowedError
import `in`.jalgaoncohelp.core.user.model.User
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.auth.principal
import io.ktor.util.pipeline.PipelineContext

suspend fun PipelineContext<Unit, ApplicationCall>.authorize(
    permission: UserPermissions,
    continueCall: suspend PipelineContext<Unit, ApplicationCall>.(User) -> Unit
) {
    val user = call.principal<UserPrincipal>()?.user ?: authenticationError()
    val isUserPermitted = user hasPermission permission

    if (!isUserPermitted) {
        notAllowedError()
    }
    continueCall(this, user)
}
