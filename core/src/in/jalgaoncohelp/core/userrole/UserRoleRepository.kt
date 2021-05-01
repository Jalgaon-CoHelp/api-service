package `in`.jalgaoncohelp.core.userrole

import java.util.*

interface UserRoleRepository {
    suspend fun addUserRole(id: Long, roleId: Int)
}