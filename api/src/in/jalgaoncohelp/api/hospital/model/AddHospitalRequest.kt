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

import `in`.jalgaoncohelp.api.utils.capitalize
import `in`.jalgaoncohelp.api.utils.validPhoneNumber
import `in`.jalgaoncohelp.core.hospitals.model.NewHospitalParams
import kotlinx.serialization.Serializable

@Serializable
data class AddHospitalRequest(
    val name: String,
    val address: String? = null,
    val talukaId: Int,
    val contact1: String? = null,
    val contact2: String? = null,
    val beds: Beds
) {
    val hospitalName by capitalize(name)

    val formattedAddress: String?
        get() = address?.let {
            val formattedAddress by capitalize(it)
            return@let formattedAddress
        }

    val validatedContact1: String?
        get() = contact1?.let {
            val contact by validPhoneNumber(it)
            return@let contact
        }

    val validatedContact2: String?
        get() = contact2?.let {
            val contact by validPhoneNumber(it)
            return@let contact
        }

    fun toHospitalParams() = NewHospitalParams(
        name = hospitalName,
        address = formattedAddress,
        talukaId = talukaId,
        contact1 = validatedContact1,
        contact2 = validatedContact2,
        beds = `in`.jalgaoncohelp.core.hospitals.model.Beds(
            general = beds.general,
            oxygen = beds.oxygen,
            icu = beds.icu,
            ventilator = beds.ventilator
        )
    )
}
