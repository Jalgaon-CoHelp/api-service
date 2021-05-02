package `in`.jalgaoncohelp.core.hospitals

import `in`.jalgaoncohelp.core.hospitals.model.Hospital
import `in`.jalgaoncohelp.core.hospitals.model.NewHospitalParams
import `in`.jalgaoncohelp.core.models.Page

interface HospitalRepository {
    suspend fun addHospital(hospitalParams: NewHospitalParams)
    suspend fun getRecentlyUpdatedHospitals(page: Page): List<Hospital>
    suspend fun getTotalHospitalsCount(): Long
}