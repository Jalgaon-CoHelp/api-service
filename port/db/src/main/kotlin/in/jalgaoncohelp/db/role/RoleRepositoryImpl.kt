package `in`.jalgaoncohelp.db.role

import `in`.jalgaoncohelp.core.exception.notFoundError
import `in`.jalgaoncohelp.core.roles.Role
import `in`.jalgaoncohelp.core.roles.RoleInfo
import `in`.jalgaoncohelp.core.roles.RoleRepository
import `in`.jalgaoncohelp.db.JalgaonCoHelpDatabase
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.withContext

class RoleRepositoryImpl(
    private val db: JalgaonCoHelpDatabase,
    private val ioContext: CoroutineContext
) : RoleRepository {
    override suspend fun findRoleInfo(role: Role): RoleInfo = withContext(ioContext) {
        db.rolesQueries.findRoleByName(role.role).executeAsOneOrNull()?.let {
            RoleInfo(it.id, Role.findByName(it.name))
        } ?: notFoundError("Role not found")
    }

}