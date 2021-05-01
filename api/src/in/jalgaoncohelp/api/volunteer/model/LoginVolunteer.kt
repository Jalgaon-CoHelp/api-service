package `in`.jalgaoncohelp.api.volunteer.model

import kotlinx.serialization.Serializable

@Serializable
class LoginVolunteerRequest(
    val email: String,
    val password: String
)

@Serializable
class LoginVolunteerResponse(
    val token: String
)