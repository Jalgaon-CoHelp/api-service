package `in`.jalgaoncohelp.api.resource.model

import `in`.jalgaoncohelp.api.model.PagedResponse
import `in`.jalgaoncohelp.core.resource.model.Resource
import kotlinx.serialization.Serializable

@Serializable
data class ResourceResponse(
    val id: Long,
    val type: String,
    val contactName: String,
    val mobileNumber: String,
    val resourceName: String,
    val address: String? = null,
    val taluka: String,
    val note: String? = null,
    val createdAt: Long
) {
    companion object {
        fun from(resource: Resource) = ResourceResponse(
            id = resource.id,
            type = resource.type.code,
            contactName = resource.contactName,
            mobileNumber = resource.mobileNumber,
            resourceName = resource.resourceName.code,
            address = resource.address,
            taluka = resource.taluka,
            note = resource.note,
            createdAt = resource.createdAtMillis
        )
    }
}

@Serializable
class PagedResourceResponse(
    override val total: Long,
    override val pages: Long,
    val resources: List<ResourceResponse>
) : PagedResponse