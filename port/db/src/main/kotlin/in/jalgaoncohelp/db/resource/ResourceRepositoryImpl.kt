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
package `in`.jalgaoncohelp.db.resource

import com.squareup.sqldelight.sqlite.driver.asJdbcDriver
import `in`.jalgaoncohelp.core.models.Page
import `in`.jalgaoncohelp.core.resource.ResourceRepository
import `in`.jalgaoncohelp.core.resource.model.NewResourceParams
import `in`.jalgaoncohelp.core.resource.model.Resource
import `in`.jalgaoncohelp.core.resource.model.ResourceName
import `in`.jalgaoncohelp.core.resource.model.ResourceType
import `in`.jalgaoncohelp.db.JalgaonCoHelpDatabase
import `in`.jalgaoncohelp.db.resource.query.FilterResourcesQuery
import kotlinx.coroutines.withContext
import javax.sql.DataSource
import kotlin.coroutines.CoroutineContext

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
