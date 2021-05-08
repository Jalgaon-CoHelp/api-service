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
package `in`.jalgaoncohelp.core.volunteer

import `in`.jalgaoncohelp.core.authentication.PasswordEncryptor
import `in`.jalgaoncohelp.core.mail.EmailRepository
import `in`.jalgaoncohelp.core.roles.Role
import `in`.jalgaoncohelp.core.roles.RoleRepository
import `in`.jalgaoncohelp.core.user.UserService
import `in`.jalgaoncohelp.core.userrole.UserRoleRepository
import `in`.jalgaoncohelp.core.utils.generatePassword

class AddVolunteer(
    private val userService: UserService,
    private val userRoleRepository: UserRoleRepository,
    private val roleRepository: RoleRepository,
    private val emailRepository: EmailRepository,
    private val passwordEncryptor: PasswordEncryptor
) {
    suspend fun add(
        name: String,
        email: String,
        phone: String,
        talukaId: Int
    ) {
        if (userService.isEmailAlreadyUsed(email)) {
            error("Email is already registered")
        }
        val plainPassword = generatePassword()
        val encryptedPassword = passwordEncryptor.encrypt(plainPassword)

        userService.addUser(name, email, phone, encryptedPassword, talukaId)

        val userId = userService.findUserIdByEmail(email)
        val volunteerRoleId = roleRepository.findRoleInfo(Role.Volunteer).id

        userRoleRepository.addUserRole(userId, volunteerRoleId)

        sendCredentialsOverEmail(name, email, plainPassword)
    }

    private fun sendCredentialsOverEmail(name: String, email: String, password: String) {
        emailRepository.sendEmail(
            to = email,
            subject = "Thank you for being part of Jalgaon CoHelp",
            body = generateEmailContent(name, email, password)
        )
    }

    private fun generateEmailContent(name: String, email: String, password: String) = buildString {
        val content = """Hello $name <br><br>
               Thank you for registering yourself as a volunteer with <b>Jalgaon CoHelp</b>.
               As a volunteer, you'll be able to contribute resources information.
               Let's help citizens of Jalgaon District together!
               <br>
               <br>
               Here are your login credentials. Visit https://jalgaoncohelp.in and enter below credentials. <br><br>
               <b>Email:</b> $email <br>
               <b>Password:</b> $password
               <br>
               <br>
               Thank you!
            """.trimIndent()
        append(content)
    }
}
