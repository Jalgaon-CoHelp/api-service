package `in`.jalgaoncohelp.core.user

import java.util.*

class UserService(private val userRepository: UserRepository) {
    suspend fun isEmailAlreadyUsed(email: String): Boolean = userRepository.isEmailAlreadyUsed(email)

    suspend fun findUserById(id: Long): User = userRepository.findUserById(id)

    suspend fun findUserByEmail(email: String): Long = userRepository.findUserByEmail(email)

    suspend fun findUserByEmailAndPassword(
        email: String,
        password: String
    ): User = userRepository.findUserByEmailAndPassword(email, password)

    suspend fun addUser(
        name: String,
        email: String,
        phone: String,
        password: String,
        talukaId: Int
    ) = userRepository.addUser(name, email, phone, password, talukaId)
}