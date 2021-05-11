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
package `in`.jalgaoncohelp.api.resource

import `in`.jalgaoncohelp.api.utils.sendResponse
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.ResourceRoute() {
    post("/request") {
        val controller by closestDI().instance<ResourceController>()
        val response = controller.addRequestResource(request = call.receive())
        sendResponse(response)
    }

    post("/supply") {
        val controller by closestDI().instance<ResourceController>()
        val response = controller.addSupplyResource(request = call.receive())
        sendResponse(response)
    }

    get("") {
        val controller by closestDI().instance<ResourceController>()

        val page = call.request.queryParameters["page"]?.toLongOrNull()
        val limit = call.request.queryParameters["limit"]?.toLongOrNull()
        val talukaId = call.request.queryParameters["talukaId"]?.toIntOrNull()
        val type = call.request.queryParameters["type"]
        val resource = call.request.queryParameters["resource"]

        val response = controller.findResources(page, limit, type, resource, talukaId)
        sendResponse(response)
    }
}
