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

import AddRequestResourceRequest
import `in`.jalgaoncohelp.api.model.Response
import `in`.jalgaoncohelp.api.model.Success
import `in`.jalgaoncohelp.api.model.Unsuccessful
import `in`.jalgaoncohelp.api.resource.model.AddSupplierResourceRequest
import `in`.jalgaoncohelp.api.resource.model.PagedResourceResponse
import `in`.jalgaoncohelp.api.resource.model.ResourceResponse
import `in`.jalgaoncohelp.core.models.Page
import `in`.jalgaoncohelp.core.resource.ResourceService
import `in`.jalgaoncohelp.core.resource.model.ResourceName
import `in`.jalgaoncohelp.core.resource.model.ResourceType

class ResourceController(private val resourceService: ResourceService) {

    suspend fun addRequestResource(request: AddRequestResourceRequest): Response = try {
        resourceService.addResource(request.toNewResourceParams())
        Success("OK")
    } catch (e: Exception) {
        Unsuccessful.Failed(e)
    }

    suspend fun addSupplyResource(request: AddSupplierResourceRequest): Response = try {
        resourceService.addResource(request.toNewResourceParams())
        Success("OK")
    } catch (e: Exception) {
        Unsuccessful.Failed(e)
    }

    suspend fun findResources(
        page: Long?,
        limit: Long?,
        type: String?,
        resource: String?,
        talukaId: Int?
    ): Response = try {
        val resources = resourceService.filterResources(
            page = Page(page = page ?: 0, limit = limit ?: 10),
            type = type?.let { ResourceType.byCode(it) },
            resource = resource?.let { ResourceName.byCode(it) },
            talukaId = talukaId
        ).let {
            PagedResourceResponse(
                it.records,
                it.pages,
                it.list.map { resource -> ResourceResponse.from(resource) }
            )
        }

        Success(resources)
    } catch (e: Exception) {
        Unsuccessful.Failed(e)
    }
}
