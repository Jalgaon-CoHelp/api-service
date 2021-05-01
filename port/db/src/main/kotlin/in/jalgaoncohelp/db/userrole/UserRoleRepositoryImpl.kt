package `in`.jalgaoncohelp.db.userrole

import `in`.jalgaoncohelp.core.userrole.UserRoleRepository
import `in`.jalgaoncohelp.db.JalgaonCoHelpDatabase
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.withContext

class UserRoleRepositoryImpl(
    private val db: JalgaonCoHelpDatabase,
    private val ioContext: CoroutineContext
) : UserRoleRepository {

    override suspend fun addUserRole(id: Long, roleId: Int) = withContext(ioContext) {
        db.userRolesQueries.addUserRole(id, roleId)
    }
}