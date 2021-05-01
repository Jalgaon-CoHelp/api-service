package `in`.jalgaoncohelp.db.user

import `in`.jalgaoncohelp.core.exception.notFoundError
import `in`.jalgaoncohelp.core.roles.Role
import `in`.jalgaoncohelp.core.user.User
import `in`.jalgaoncohelp.core.user.UserRepository
import `in`.jalgaoncohelp.db.JalgaonCoHelpDatabase
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val db: JalgaonCoHelpDatabase,
    private val ioContext: CoroutineContext
) : UserRepository {

    override suspend fun isEmailAlreadyUsed(email: String): Boolean = withContext(ioContext) {
        db.usersQueries.isEmailAlreadyExists(email)
            .executeAsList()
            .isNotEmpty()
    }

    override suspend fun addUser(
        name: String,
        email: String,
        phone: String,
        password: String,
        talukaId: Int
    ) = withContext(ioContext) {
        runCatching { db.usersQueries.addUser(name, email, phone, password, talukaId) }
            .getOrElse { error("Failed to Add user") }
    }

    override suspend fun findUserById(id: Long): User = withContext(ioContext) {
        db.usersQueries.findUserById(id)
            .executeAsOneOrNull()
            ?.let {
                User(
                    id = it.user_id,
                    name = it.name,
                    email = it.email,
                    phone = it.phone,
                    taluka = it.taluka,
                    role = Role.findByName(it.role)
                )
            } ?: notFoundError("User not exists")
    }

    override suspend fun findUserByEmail(email: String): Long = withContext(ioContext) {
        return@withContext db.usersQueries.findUserByEmail(email).executeAsOneOrNull()
            ?: notFoundError("User not exists")
    }

    override suspend fun findUserByEmailAndPassword(email: String, password: String): User = withContext(ioContext) {
        db.usersQueries.findUserByEmailAndPassword(email, password)
            .executeAsOneOrNull()
            ?.let {
                User(
                    id = it.user_id,
                    name = it.name,
                    email = it.email,
                    phone = it.phone,
                    taluka = it.taluka,
                    role = Role.findByName(it.role)
                )
            } ?: notFoundError("User not exists")
    }
}