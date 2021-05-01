package `in`.jalgaoncohelp.api.authentication

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm

class JwtController(private val spec: JwtSpec) {

    private val algorithm = Algorithm.HMAC256(spec.secret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(spec.issuer)
        .withAudience(spec.issuer)
        .build()

    /**
     * Generates JWT token from [userId].
     */
    fun sign(userId: String): String = JWT
        .create()
        .withIssuer(spec.issuer)
        .withAudience(spec.audience)
        .withClaim(ClAIM, userId)
        .sign(algorithm)

    companion object {
        const val ClAIM = "userId"
    }

}