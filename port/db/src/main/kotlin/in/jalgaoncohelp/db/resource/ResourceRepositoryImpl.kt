package `in`.jalgaoncohelp.db.resource

import `in`.jalgaoncohelp.core.models.Page
import `in`.jalgaoncohelp.core.resource.ResourceRepository
import `in`.jalgaoncohelp.core.resource.model.NewResourceParams
import `in`.jalgaoncohelp.core.resource.model.Resource
import `in`.jalgaoncohelp.core.resource.model.ResourceName
import `in`.jalgaoncohelp.core.resource.model.ResourceType
import `in`.jalgaoncohelp.db.JalgaonCoHelpDatabase
import `in`.jalgaoncohelp.db.resource.query.FilterResourcesQuery
import com.squareup.sqldelight.sqlite.driver.asJdbcDriver
import javax.sql.DataSource
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.withContext

class ResourceRepositoryImpl(
    private val dataSource: DataSource,
    private val ioContext: CoroutineContext
) : ResourceRepository {

    private val db = JalgaonCoHelpDatabase(dataSource.asJdbcDriver())

    override suspend fun addResource(params: NewResourceParams) = withContext(ioContext) {
        db.resourcesQueries.addResource(
            type = params.type.code,
            contact_name = params.contactName,
            mobile_number = params.mobileNumber,
            resource_name = params.resourceName.code,
            address = params.address,
            taluka_id = params.talukaId,
            note = params.note
        )
    }

    override suspend fun findResources(
        page: Page,
        type: ResourceType?,
        resource: ResourceName?,
        talukaId: Int?
    ): List<Resource> = withContext(ioContext) {
        runCatching {
            FilterResourcesQuery(dataSource).find(page, type?.code, resource?.code, talukaId)
        }.getOrElse {
            it.printStackTrace()
            error("Failed to load resources")
        }
    }

    override suspend fun countResources(
        type: ResourceType?,
        resource: ResourceName?,
        talukaId: Int?
    ): Long = withContext(ioContext) {
        runCatching {
            FilterResourcesQuery(dataSource).count(type?.code, resource?.code, talukaId)
        }.getOrElse {
            it.printStackTrace()
            error("Failed to load count of resources")
        }
    }
}