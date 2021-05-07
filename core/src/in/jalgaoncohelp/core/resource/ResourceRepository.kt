package `in`.jalgaoncohelp.core.resource

import `in`.jalgaoncohelp.core.models.Page
import `in`.jalgaoncohelp.core.resource.model.NewResourceParams
import `in`.jalgaoncohelp.core.resource.model.Resource
import `in`.jalgaoncohelp.core.resource.model.ResourceName
import `in`.jalgaoncohelp.core.resource.model.ResourceType

interface ResourceRepository {
    suspend fun addResource(params: NewResourceParams)
    suspend fun findResources(page: Page, type: ResourceType?, resource: ResourceName?, talukaId: Int?): List<Resource>
    suspend fun countResources(type: ResourceType?, resource: ResourceName?, talukaId: Int?): Long
}