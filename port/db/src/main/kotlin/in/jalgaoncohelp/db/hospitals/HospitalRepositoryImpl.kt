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
import `in`.jalgaoncohelp.core.hospitals.model.Beds
import `in`.jalgaoncohelp.core.hospitals.model.Hospital
import `in`.jalgaoncohelp.core.hospitals.model.NewHospitalParams
import `in`.jalgaoncohelp.core.models.Page
import `in`.jalgaoncohelp.core.taluka.model.Taluka
import `in`.jalgaoncohelp.db.JalgaonCoHelpDatabase
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.withContext

class
HospitalRepositoryImpl(
    private val db: JalgaonCoHelpDatabase,
    private val ioContext: CoroutineContext
) : HospitalRepository {

    override suspend fun addHospital(hospitalParams: NewHospitalParams) = withContext(ioContext) {
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
    }

    override suspend fun getRecentlyUpdatedHospitals(page: Page): List<Hospital> = withContext(ioContext) {
        db.hospitalsQueries.getRecentlyUpdatedHospitals(page.limit, page.offset)
            .executeAsList()
            .map {
                Hospital(
                    id = it.id,
                    name = it.name,
                    address = it.address,
                    taluka = Taluka(it.taluka_id, it.taluka_name),
                    contact1 = it.contact_1,
                    contact2 = it.contact_2,
                    beds = Beds(
                        general = it.bed_general,
                        oxygen = it.bed_oxygen,
                        icu = it.bed_icu,
                        ventilator = it.bed_ventilator
                    ),
                    createdAt = it.created_at,
                    updatedAt = it.updated_at
                )
            }
    }

    override suspend fun getTotalHospitalsCount(): Long {
        return db.hospitalsQueries.getTotalHospitals().executeAsOne()
    }
}
