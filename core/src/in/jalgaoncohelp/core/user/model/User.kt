package `in`.jalgaoncohelp.core.user.model

import `in`.jalgaoncohelp.core.roles.Role

data class User(
    val id: Long,
    val name: String,
    val email: String,
    val phone: String,
    val taluka: String,
    val role: Role
)