package `in`.jalgaoncohelp.api.model

import kotlinx.serialization.Serializable

interface PagedResponse {
    val total: Long
    val pages: Long
}