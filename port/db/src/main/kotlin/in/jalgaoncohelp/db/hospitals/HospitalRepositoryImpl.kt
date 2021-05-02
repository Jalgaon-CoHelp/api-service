/*
 * MIT License
 *
 * Copyright (c) 2021 Jalgaon CoHelp
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package `in`.jalgaoncohelp.db.hospitals

import `in`.jalgaoncohelp.core.hospitals.HospitalRepository
import `in`.jalgaoncohelp.core.hospitals.model.BedType
import `in`.jalgaoncohelp.core.hospitals.model.Hospital
import `in`.jalgaoncohelp.core.hospitals.model.NewHospitalParams
import `in`.jalgaoncohelp.core.models.Page
import `in`.jalgaoncohelp.db.JalgaonCoHelpDatabase
import `in`.jalgaoncohelp.db.hospitals.query.FilterRecentlyUpdatedHospitalsQuery
import com.squareup.sqldelight.sqlite.driver.asJdbcDriver
import javax.sql.DataSource
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.withContext

class
HospitalRepositoryImpl(
    private val dataSource: DataSource,
    private val ioContext: CoroutineContext
) : HospitalRepository {

    override suspend fun addHospital(hospitalParams: NewHospitalParams) = withContext(ioContext) {
        runCatching {
            val db = JalgaonCoHelpDatabase(dataSource.asJdbcDriver())
            db.hospitalsQueries.addHospital(
                name = hospitalParams.name,
                address = hospitalParams.address,
                taluka_id = hospitalParams.talukaId,
                contact_1 = hospitalParams.contact1,
                contact_2 = hospitalParams.contact2,
                bed_general = hospitalParams.beds.general,
                bed_icu = hospitalParams.beds.icu,
                bed_oxygen = hospitalParams.beds.oxygen,
                bed_ventilator = hospitalParams.beds.ventilator
            )
        }.getOrElse { error("Failed to add hospital") }
    }

    override suspend fun getRecentlyUpdatedHospitals(
        page: Page,
        talukaId: Int?,
        bedType: BedType?
    ): List<Hospital> = withContext(ioContext) {
        runCatching {
            FilterRecentlyUpdatedHospitalsQuery(dataSource).findRecentlyUpdatedHospitals(page, talukaId, bedType)
        }.getOrElse {
            it.printStackTrace()
            error("Failed to load hospitals")
        }
    }

    override suspend fun getTotalHospitalsCount(talukaId: Int?, bedType: BedType?): Long = runCatching {
        return FilterRecentlyUpdatedHospitalsQuery(dataSource).countRecentlyUpdatedHospitals(talukaId, bedType)
    }.getOrDefault(0)
}
