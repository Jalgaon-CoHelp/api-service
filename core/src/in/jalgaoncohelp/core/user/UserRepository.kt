package `in`.jalgaoncohelp.core.user

import java.util.*

interface UserRepository {
    suspend fun isEmailAlreadyUsed(email: String): Boolean
    suspend fun findUserById(id: Long): User
    suspend fun findUserByEmail(email: String): Long
    suspend fun findUserByEmailAndPassword(email: String, password: String): User
    suspend fun addUser(name: String, email: String, phone: String, password: String, talukaId: Int)
}