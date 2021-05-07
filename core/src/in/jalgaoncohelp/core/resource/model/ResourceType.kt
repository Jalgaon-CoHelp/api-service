package `in`.jalgaoncohelp.core.resource.model

import `in`.jalgaoncohelp.core.exception.notFoundError

enum class ResourceType(val code: String) {
    Requested("request"),
    Supplied("supply");

    companion object {
        fun byCode(code: String): ResourceType {
            return ResourceType.values().find { it.code == code } ?: notFoundError("Resource not exists with this code")
        }
    }
}