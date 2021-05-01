package `in`.jalgaoncohelp.core.volunteer

import `in`.jalgaoncohelp.core.authentication.PasswordEncryptor
import `in`.jalgaoncohelp.core.mail.EmailRepository
import `in`.jalgaoncohelp.core.user.UserService
import `in`.jalgaoncohelp.core.userrole.UserRoleRepository
import `in`.jalgaoncohelp.core.utils.generatePassword

class AddVolunteer(
    private val userService: UserService,
    private val userRoleRepository: UserRoleRepository,
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
        val id = userService.findUserByEmail(email)
        // Hardcoding volunteer ID as of now.
        // Later on, If multiple roles exists, this should be moved to the RoleService.
        val volunteerRoleId = 1

        userRoleRepository.addUserRole(id, volunteerRoleId)

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