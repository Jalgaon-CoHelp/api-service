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