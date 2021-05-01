package `in`.jalgaoncohelp.api.volunteer.model

import `in`.jalgaoncohelp.api.utils.trimmed
import `in`.jalgaoncohelp.api.utils.validEmail
import `in`.jalgaoncohelp.api.utils.validMobileNumber
import kotlinx.serialization.Serializable

@Serializable
class NewVolunteerRequest(
    private val name: String,
    private val email: String,
    private val mobile: String,
    val talukaId: Int
) {
    val validName by trimmed(name)
    val validEmail by validEmail(email)
    val validMobile by validMobileNumber(mobile)
}