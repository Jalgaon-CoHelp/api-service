package `in`.jalgaoncohelp.api.hospital.model

import `in`.jalgaoncohelp.api.model.PagedResponse
import `in`.jalgaoncohelp.api.taluka.model.TalukaResponse
import `in`.jalgaoncohelp.core.hospitals.model.Hospital
import kotlinx.serialization.Serializable

@Serializable
data class HospitalResponse(
    val id: Long,
    val name: String,
    val address: String?,
    val taluka: TalukaResponse,
    val contact1: String?,
    val contact2: String?,
    val beds: Beds,
    val createdAt: Long,
    val updatedAt: Long
) {
    companion object {
        fun from(hospital: Hospital) = HospitalResponse(
            id = hospital.id,
            name = hospital.name,
            address = hospital.address,
            taluka = TalukaResponse(hospital.taluka.id, hospital.taluka.name),
            contact1 = hospital.contact1,
            contact2 = hospital.contact2,
            beds = Beds.from(hospital.beds),
            createdAt = hospital.createdAtMillis,
            updatedAt = hospital.updatedAtMillis
        )
    }
}

@Serializable
class PagedHospitalResponse(
    override val total: Long,
    override val pages: Long,
    val hospitals: List<HospitalResponse>
) : PagedResponse
