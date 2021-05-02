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

    suspend fun getHospitals(page: Long?, limit: Long?, talukaId: Int?, bedType: String?): Response = try {
        val type = bedType?.let {
            if (it.isNotBlank()) {
                hospitalService.findHospitalBedType(it)
            } else null
        }
        val hospitals = hospitalService.filterHospitals(
            page = Page(page ?: 0, limit ?: 10),
            talukaId = talukaId,
            bedType = type
        ).let {
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
