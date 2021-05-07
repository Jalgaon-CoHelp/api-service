import `in`.jalgaoncohelp.api.utils.capitalize
import `in`.jalgaoncohelp.api.utils.validMobileNumber
import `in`.jalgaoncohelp.core.resource.model.NewResourceParams
import `in`.jalgaoncohelp.core.resource.model.ResourceName
import `in`.jalgaoncohelp.core.resource.model.ResourceType
import kotlinx.serialization.Serializable

@Serializable
data class AddRequestResourceRequest(
    val contactName: String,
    val mobileNumber: String,
    val resourceName: String,
    val address: String? = null,
    val talukaId: Int,
    val note: String? = null
) {
    val formattedContactName by capitalize(contactName)

    val validatedMobileNumber by validMobileNumber(mobileNumber)

    val formattedAddress: String?
        get() = address?.let {
            val formattedAddress by capitalize(it)
            return@let formattedAddress
        }

    fun toNewResourceParams() = NewResourceParams(
        type = ResourceType.Requested,
        contactName = formattedContactName,
        mobileNumber = validatedMobileNumber,
        resourceName = ResourceName.byCode(resourceName),
        address = formattedAddress,
        talukaId = talukaId,
        note = note
    )
}
