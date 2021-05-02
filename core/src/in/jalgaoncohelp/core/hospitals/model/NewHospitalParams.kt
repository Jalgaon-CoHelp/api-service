package `in`.jalgaoncohelp.core.hospitals.model

data class NewHospitalParams(
    val name: String,
    val address: String?,
    val talukaId: Int,
    val contact1: String?,
    val contact2: String?,
    val beds: Beds
)
