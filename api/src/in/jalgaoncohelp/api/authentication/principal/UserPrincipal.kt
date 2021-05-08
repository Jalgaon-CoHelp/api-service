package `in`.jalgaoncohelp.api.authentication.principal

import `in`.jalgaoncohelp.core.user.model.User
import io.ktor.auth.Principal

data class UserPrincipal(val user: User) : Principal