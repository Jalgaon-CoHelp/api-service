package `in`.jalgaoncohelp.core.user

import `in`.jalgaoncohelp.core.roles.Role
import java.util.*

data class User(
    val id: Long,
    val name: String,
    val email: String,
    val phone: String,
    val taluka: String,
    val role: Role
)
