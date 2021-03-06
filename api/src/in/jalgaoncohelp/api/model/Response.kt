/*
 * MIT License
 *
 * Copyright (c) 2021 Jalgaon CoHelp
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package `in`.jalgaoncohelp.api.model

import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Serializable

sealed class Response(val statusCode: HttpStatusCode)

class Success<T : Any>(val data: @Serializable T) : Response(HttpStatusCode.OK)

sealed class Unsuccessful(e: Throwable, statusCode: HttpStatusCode) : Response(statusCode) {
    val response = UnsuccessfulResponse(e.message.toString())

    class NotFound<T>(e: Throwable = Exception("Not found")) : Unsuccessful(e, HttpStatusCode.NotFound)
    class Failed(e: Throwable = Exception("Failed to satisfy request")) : Unsuccessful(e, HttpStatusCode.BadRequest)
    class Unauth(e: Throwable = Exception("Authentication Failed")) : Unsuccessful(e, HttpStatusCode.Unauthorized)

    @Serializable
    class UnsuccessfulResponse(val message: String)
}

fun unsuccessfulResponse(message: String?) = Unsuccessful.UnsuccessfulResponse(message ?: "Unknown error occurred!")
