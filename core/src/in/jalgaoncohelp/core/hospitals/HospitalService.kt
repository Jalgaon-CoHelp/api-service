package `in`.jalgaoncohelp.core.hospitals

import `in`.jalgaoncohelp.core.hospitals.model.Hospital
import `in`.jalgaoncohelp.core.hospitals.model.NewHospitalParams
import `in`.jalgaoncohelp.core.models.Page
import `in`.jalgaoncohelp.core.models.PagedList

class HospitalService(private val repository: HospitalRepository) {
    suspend fun addHospital(hospitalParams: NewHospitalParams) = repository.addHospital(hospitalParams)

    suspend fun getRecentlyUpdatedHospitals(page: Page): PagedList<Hospital> {
        return PagedList(
            records = repository.getTotalHospitalsCount(),
            list = repository.getRecentlyUpdatedHospitals(page),
            limit = page.limit
        )
    }
}