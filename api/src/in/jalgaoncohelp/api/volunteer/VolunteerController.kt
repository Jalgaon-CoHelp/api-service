package `in`.jalgaoncohelp.api.volunteer

import `in`.jalgaoncohelp.api.authentication.JwtController
import `in`.jalgaoncohelp.api.model.Response
import `in`.jalgaoncohelp.api.model.Success
import `in`.jalgaoncohelp.api.model.Unsuccessful
import `in`.jalgaoncohelp.api.volunteer.model.LoginVolunteerRequest
import `in`.jalgaoncohelp.api.volunteer.model.LoginVolunteerResponse
import `in`.jalgaoncohelp.api.volunteer.model.NewVolunteerRequest
import `in`.jalgaoncohelp.core.authentication.PasswordEncryptor
import `in`.jalgaoncohelp.core.user.UserService
import `in`.jalgaoncohelp.core.volunteer.AddVolunteer

class VolunteerController(
    private val addVolunteer: AddVolunteer,
    private val userService: UserService,
    private val passwordEncryptor: PasswordEncryptor,
    private val jwtController: JwtController
) {
    suspend fun addVolunteer(volunteer: NewVolunteerRequest): Response = try {
        addVolunteer.add(volunteer.validName, volunteer.validEmail, volunteer.validMobile, volunteer.talukaId)
        Success("OK")
    } catch (e: Exception) {
        e.printStackTrace()
        Unsuccessful.Failed(e)
    }

    suspend fun signInWithEmailAndPassword(login: LoginVolunteerRequest): Response = try {
        val email = login.email
        val password = passwordEncryptor.encrypt(login.password)

        val user = userService.findUserByEmailAndPassword(email, password)
        val token = jwtController.sign(user.email)

        Success(LoginVolunteerResponse(token))
    } catch (e: Exception) {
        e.printStackTrace()
        Unsuccessful.Unauth()
    }
}