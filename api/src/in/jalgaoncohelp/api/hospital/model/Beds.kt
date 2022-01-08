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

import kotlinx.serialization.Serializable
import `in`.jalgaoncohelp.core.hospitals.model.Beds as CoreBed

@Serializable
data class Beds(
    val general: Int?,
    val oxygen: Int?,
    val icu: Int?,
    val ventilator: Int?
) {
    init {
        listOf(general, oxygen, icu, ventilator).forEach { count ->
            count?.let {
                if (count <= -1) {
                    error("Bed count can't be negative")
                }
            }
        }
    }

    fun toBedModel() = `in`.jalgaoncohelp.core.hospitals.model.Beds(
        general = general,
        oxygen = oxygen,
        icu = icu,
        ventilator = ventilator
    )

    companion object {
        fun from(beds: CoreBed) = Beds(
            general = beds.general,
            oxygen = beds.oxygen,
            icu = beds.icu,
            ventilator = beds.ventilator
        )
    }
}
