package `in`.jalgaoncohelp.application.authentication

import `in`.jalgaoncohelp.core.authentication.PasswordEncryptor
import io.ktor.util.KtorExperimentalAPI
import io.ktor.util.hex
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class DefaultPasswordEncryptor(secret: String): PasswordEncryptor {
    private val hmacKey: SecretKeySpec = SecretKeySpec(secret.toByteArray(), ALGORITHM)

    /**
     * Function which encrypts password and return
     */
    @KtorExperimentalAPI
    override fun encrypt(password: String): String {
        val hmac = Mac.getInstance(ALGORITHM).apply { init(hmacKey) }
        return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
    }

    companion object {
        const val ALGORITHM = "HmacSHA256"
    }
}