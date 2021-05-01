package `in`.jalgaoncohelp.core.roles

enum class Role(private val role: String) {
    Volunteer("volunteer");

    companion object {
        fun findByName(name: String): Role {
            return values().find { it.role == name } ?: error("Role not exists with name '$name'")
        }
    }
}