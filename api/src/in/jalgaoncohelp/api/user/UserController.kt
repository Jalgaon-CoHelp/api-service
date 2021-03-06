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
package `in`.jalgaoncohelp.api.user

import `in`.jalgaoncohelp.api.authentication.jwt.JwtController
import `in`.jalgaoncohelp.api.exception.AuthenticationException
import `in`.jalgaoncohelp.api.model.Response
import `in`.jalgaoncohelp.api.model.Success
import `in`.jalgaoncohelp.api.model.Unsuccessful
import `in`.jalgaoncohelp.api.user.model.LoginRequest
import `in`.jalgaoncohelp.api.user.model.LoginResponse
import `in`.jalgaoncohelp.api.user.model.NewVolunteerRequest
import `in`.jalgaoncohelp.api.user.model.UserInfo
import `in`.jalgaoncohelp.core.authentication.PasswordEncryptor
import `in`.jalgaoncohelp.core.user.UserService
import `in`.jalgaoncohelp.core.volunteer.AddVolunteer

class UserController(
    private val addVolunteer: AddVolunteer,
    private val userService: UserService,
    private val passwordEncryptor: PasswordEncryptor,
    private val jwtController: JwtController
) {
    suspend fun addVolunteer(volunteer: NewVolunteerRequest): Response = try {
        addVolunteer.add(
            name = volunteer.validName,
            email = volunteer.validEmail,
            password = volunteer.validPassword,
            phone = volunteer.validMobile,
            talukaId = volunteer.talukaId
        )
        Success("OK")
    } catch (e: Exception) {
        e.printStackTrace()
        Unsuccessful.Failed(e)
    }

    suspend fun signInWithEmailAndPassword(login: LoginRequest): Response = try {
        val email = login.email
        val password = passwordEncryptor.encrypt(login.password)

        val user = userService.findUserByEmailAndPassword(email, password)
        val token = jwtController.sign(user.email)

        Success(LoginResponse(token, UserInfo.from(user)))
    } catch (e: Exception) {
        e.printStackTrace()
        Unsuccessful.Unauth(AuthenticationException("Invalid email / password"))
    }
}
