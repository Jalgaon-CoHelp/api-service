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
package `in`.jalgaoncohelp.api.resource.model

import `in`.jalgaoncohelp.api.utils.delegates.capitalize
import `in`.jalgaoncohelp.api.utils.delegates.validMobileNumber
import `in`.jalgaoncohelp.core.resource.model.NewResourceParams
import `in`.jalgaoncohelp.core.resource.model.ResourceName
import `in`.jalgaoncohelp.core.resource.model.ResourceType
import kotlinx.serialization.Serializable

@Serializable
data class AddSupplierResourceRequest(
    val contactName: String,
    val mobileNumber: String,
    val resourceName: String,
    val address: String? = null,
    val talukaId: Int,
    val note: String? = null
) {
    val formattedContactName by capitalize(contactName)

    val validatedMobileNumber by validMobileNumber(mobileNumber)

    val formattedAddress: String?
        get() = address?.let {
            val formattedAddress by capitalize(it)
            return@let formattedAddress
        }

    fun toNewResourceParams() = NewResourceParams(
        type = ResourceType.Supplied,
        contactName = formattedContactName,
        mobileNumber = validatedMobileNumber,
        resourceName = ResourceName.byCode(resourceName),
        address = formattedAddress,
        talukaId = talukaId,
        note = note
    )
}
