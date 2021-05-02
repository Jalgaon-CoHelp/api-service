package `in`.jalgaoncohelp.api.hospital.model

import `in`.jalgaoncohelp.core.hospitals.model.Beds as CoreBed
import kotlinx.serialization.Serializable

@Serializable
data class Beds(
    val general: Int?,
    val oxygen: Int?,
    val icu: Int?,
    val ventilator: Int?
) {
    companion object {
        fun from(beds: CoreBed) = Beds(
            beds.general,
            beds.oxygen,
            beds.icu,
            beds.ventilator
        )
    }
}