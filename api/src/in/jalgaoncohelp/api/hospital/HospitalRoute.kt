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

import `in`.jalgaoncohelp.api.authentication.UserPermissions
import `in`.jalgaoncohelp.api.authentication.handler.authorize
import `in`.jalgaoncohelp.api.utils.sendResponse
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.request.receive
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.put
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.HospitalRoute() {
    authenticate {
//        TODO Uncomment this route once this API is in use
//        post("/") {
//            authorize(UserPermissions.Hospital.CreateNew) {
//                val controller by closestDI().instance<HospitalController>()
//                val response = controller.addHospital(addHospitalRequest = call.receive())
//                sendResponse(response)
//            }
//        }

        put("/{id}/beds") {
            authorize(UserPermissions.Hospital.UpdateBeds) {
                val controller by closestDI().instance<HospitalController>()
                val hospitalId = call.parameters["id"]!!.toLong()
                val response = controller.updateHospitalBeds(hospitalId, updatedBeds = call.receive())
                sendResponse(response)
            }
        }
    }

    get("") {
        val controller by closestDI().instance<HospitalController>()
        val page = call.request.queryParameters["page"]?.toLongOrNull()
        val limit = call.request.queryParameters["limit"]?.toLongOrNull()
        val talukaId = call.request.queryParameters["talukaId"]?.toIntOrNull()
        val bedType = call.request.queryParameters["bedType"]

        val response = controller.getHospitals(page, limit, talukaId, bedType)
        sendResponse(response)
    }
}
