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
