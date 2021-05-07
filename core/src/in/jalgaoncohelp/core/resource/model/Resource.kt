package `in`.jalgaoncohelp.core.resource.model

import `in`.jalgaoncohelp.core.utils.epochMillis

data class Resource(
    val id: Long,
    val type: ResourceType,
    val contactName: String,
    val mobileNumber: String,
    val resourceName: ResourceName,
    val address: String?,
    val taluka: String,
    val note: String?,
    val createdAt: String
) {
    val createdAtMillis: Long get() = epochMillis(createdAt)
}
