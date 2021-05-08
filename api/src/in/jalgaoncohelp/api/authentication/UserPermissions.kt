package `in`.jalgaoncohelp.api.authentication

import `in`.jalgaoncohelp.core.roles.Role
import `in`.jalgaoncohelp.core.user.model.User

sealed class UserPermissions(val permittedRoles: List<Role>) {
    object CreateNewHospital : UserPermissions(listOf(Role.Admin))
    object UpdateHospital : UserPermissions(listOf(Role.Admin, Role.Volunteer))
    object DeleteHospital : UserPermissions(listOf(Role.Admin))
}

infix fun User.hasPermission(permissions: UserPermissions): Boolean {
    return role in permissions.permittedRoles
}
