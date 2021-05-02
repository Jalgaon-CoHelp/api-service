package `in`.jalgaoncohelp.api.hospital.model

import `in`.jalgaoncohelp.core.hospitals.model.NewHospitalParams
import kotlinx.serialization.Serializable

@Serializable
data class AddHospitalRequest(
    val name: String,
    val address: String?,
    val talukaId: Int,
    val contact1: String?,
    val contact2: String?,
    val beds: Beds
) {
    fun toHospitalParams() = NewHospitalParams(
        name = name,
        address = address,
        talukaId = talukaId,
        contact1 = contact1,
        contact2 = contact2,
        beds = `in`.jalgaoncohelp.core.hospitals.model.Beds(
            general = beds.general,
            oxygen = beds.oxygen,
            icu = beds.icu,
            ventilator = beds.ventilator
        )
    )
}


