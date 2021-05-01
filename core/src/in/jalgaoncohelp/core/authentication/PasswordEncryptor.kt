package `in`.jalgaoncohelp.core.authentication

interface PasswordEncryptor {
    fun encrypt(password: String): String
}