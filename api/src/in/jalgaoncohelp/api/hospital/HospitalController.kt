package `in`.jalgaoncohelp.api.hospital

import `in`.jalgaoncohelp.api.hospital.model.AddHospitalRequest
import `in`.jalgaoncohelp.api.hospital.model.HospitalResponse
import `in`.jalgaoncohelp.api.hospital.model.PagedHospitalResponse
import `in`.jalgaoncohelp.api.model.Response
import `in`.jalgaoncohelp.api.model.Success
import `in`.jalgaoncohelp.api.model.Unsuccessful
import `in`.jalgaoncohelp.core.hospitals.HospitalService
import `in`.jalgaoncohelp.core.models.Page

class HospitalController(private val hospitalService: HospitalService) {
    suspend fun addHospital(addHospitalRequest: AddHospitalRequest): Response = try {
        hospitalService.addHospital(addHospitalRequest.toHospitalParams())
        Success("OK")
    } catch (e: Exception) {
        Unsuccessful.Failed(e)
    }

    suspend fun getRecentlyUpdatedHospitals(page: Long?, limit: Long?): Response = try {
        val hospitals = hospitalService.getRecentlyUpdatedHospitals(Page(page ?: 0, limit ?: 10))
            .let {
                PagedHospitalResponse(
                    it.records,
                    it.pages,
                    it.list.map { hospital -> HospitalResponse.from(hospital) }
                )
            }
        Success(hospitals)
    } catch (e: Exception) {
        Unsuccessful.Failed(e)
    }
}