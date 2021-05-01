package `in`.jalgaoncohelp.api.model

import io.ktor.http.HttpStatusCode
import java.lang.Exception
import kotlinx.serialization.Serializable

sealed class Response(val statusCode: HttpStatusCode)


class Success<T: Any>(val data: @Serializable T) : Response(HttpStatusCode.OK)

sealed class Unsuccessful(e: Throwable, statusCode: HttpStatusCode) : Response(statusCode) {
    val response = UnsuccessfulResponse(e.message.toString())

    class NotFound<T>(e: Throwable = Exception("Not found")) : Unsuccessful(e, HttpStatusCode.NotFound)
    class Failed(e: Throwable = Exception("Failed to satisfy request")) : Unsuccessful(e, HttpStatusCode.BadRequest)
    class Unauth(e: Throwable = Exception("Unauth request")) : Unsuccessful(e, HttpStatusCode.Unauthorized)

    @Serializable
    class UnsuccessfulResponse(val message: String)

}

