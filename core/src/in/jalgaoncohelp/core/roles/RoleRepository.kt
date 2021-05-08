package `in`.jalgaoncohelp.core.roles

interface RoleRepository {
    suspend fun findRoleInfo(role: Role): RoleInfo
}