package `in`.jalgaoncohelp.api.utils

import `in`.jalgaoncohelp.api.model.Response
import `in`.jalgaoncohelp.api.model.Success
import `in`.jalgaoncohelp.api.model.Unsuccessful
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineContext

suspend fun PipelineContext<Unit, ApplicationCall>.sendResponse(response: Response) {
    val (statusCode, data) = when (response) {
        is Success<*> -> response.statusCode to response.data
        is Unsuccessful -> response.statusCode to response.response
    }
    call.respond(statusCode, data)
}