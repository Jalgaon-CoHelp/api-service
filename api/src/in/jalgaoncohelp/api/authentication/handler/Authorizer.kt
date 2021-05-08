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
