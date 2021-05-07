package `in`.jalgaoncohelp.core.resource.model

data class NewResourceParams(
    val type: ResourceType,
    val contactName: String,
    val mobileNumber: String,
    val resourceName: ResourceName,
    val address: String?,
    val talukaId: Int,
    val note: String?
)
