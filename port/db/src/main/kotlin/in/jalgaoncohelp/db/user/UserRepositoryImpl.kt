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
package `in`.jalgaoncohelp.db.user

import `in`.jalgaoncohelp.core.exception.notFoundError
import `in`.jalgaoncohelp.core.roles.Role
import `in`.jalgaoncohelp.core.user.UserRepository
import `in`.jalgaoncohelp.core.user.model.User
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

    override suspend fun findUserIdByEmail(email: String): Long = withContext(ioContext) {
        db.usersQueries.findUserIdByEmail(email).executeAsOneOrNull() ?: notFoundError("User not found")
    }

    override suspend fun findUserByEmail(email: String): User = withContext(ioContext) {
        db.usersQueries.findUserByEmail(email).executeAsOneOrNull()?.let {
            User(
                id = it.user_id,
                name = it.name,
                email = it.email,
                phone = it.phone,
                taluka = it.taluka_name,
                role = Role.findByName(it.role)
            )
        } ?: notFoundError("User not found")
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
