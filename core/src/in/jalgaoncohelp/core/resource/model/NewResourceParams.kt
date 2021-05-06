package `in`.jalgaoncohelp.core.resource.model

data class NewResourceParams(
    val contactName: String,
    val mobileNumber: String,
    val type: ResourceType,
    val address: String?,
    val talukaId: Int,
    val note: String?
)
