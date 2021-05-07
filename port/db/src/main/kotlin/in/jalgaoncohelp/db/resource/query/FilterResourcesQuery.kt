package `in`.jalgaoncohelp.db.resource.query

import `in`.jalgaoncohelp.core.models.Page
import `in`.jalgaoncohelp.core.resource.model.Resource
import `in`.jalgaoncohelp.core.resource.model.ResourceName
import `in`.jalgaoncohelp.core.resource.model.ResourceType
import `in`.jalgaoncohelp.db.utils.QueryAndParams
import `in`.jalgaoncohelp.db.utils.query
import `in`.jalgaoncohelp.db.utils.whereQueryAndParams
import javax.sql.DataSource


class FilterResourcesQuery(private val dataSource: DataSource) {
    fun find(
        page: Page,
        type: String? = null,
        resourceName: String? = null,
        talukaId: Int? = null,
    ): List<Resource> {
        val (query, params) = generateQueryAndParams(page, type, resourceName, talukaId)

        val resultSet = dataSource.query(query, *params)

        val resources = mutableListOf<Resource>()

        while (resultSet.next()) {
            val id = resultSet.getLong(1)
            val resourceType = ResourceType.byCode(resultSet.getString(2))
            val contactName = resultSet.getString(3)
            val mobileNumber = resultSet.getString(4)
            val resource = ResourceName.byCode(resultSet.getString(5))
            val address = resultSet.getString(6)
            val talukaId = resultSet.getInt(7)
            val talukaName = resultSet.getString(8)
            val note = resultSet.getString(9)
            val createdAt = resultSet.getString(10)

            resources.add(
                Resource(
                    id = id,
                    type = resourceType,
                    contactName = contactName,
                    mobileNumber = mobileNumber,
                    resourceName = resource,
                    address = address,
                    taluka = talukaName,
                    note = note,
                    createdAt = createdAt
                )
            )
        }

        return resources
    }

    private fun generateQueryAndParams(
        page: Page,
        type: String? = null,
        resourceName: String? = null,
        talukaId: Int? = null
    ): QueryAndParams {
        val (whereQuery, paramValues) = whereQueryAndParams(
            "r.type" to type,
            "r.resource_name" to resourceName,
            "r.taluka_id" to talukaId
        )

        val query = """
            SELECT r.id,
                   type,
                   contact_name,
                   mobile_number,
                   resource_name,
                   address,
                   taluka_id,
                   t.name AS taluka_name,
                   note,
                   created_at
            FROM resources r
                     inner join talukas t on t.id = r.taluka_id
            $whereQuery
            ORDER BY created_at DESC
            LIMIT ${page.limit} OFFSET ${page.offset};
        """.trimIndent()

        return query to paramValues
    }

    fun count(
        type: String? = null,
        resourceName: String? = null,
        talukaId: Int? = null,
    ): Long {
        val (countQuery, params) = countQuery(type, resourceName, talukaId)
        val resultSet = dataSource.query(countQuery, *params)

        resultSet.next()
        val count = resultSet.getLong(1)

        return count
    }

    private fun countQuery(
        type: String? = null,
        resourceName: String? = null,
        talukaId: Int? = null,
    ): QueryAndParams {
        val (whereQuery, params) = whereQueryAndParams(type, resourceName, talukaId)
        val query = """
            SELECT count(*) AS total_resources
            FROM resources r
            $whereQuery;
        """.trimIndent()

        return query to params
    }

    fun whereQueryAndParams(
        type: String? = null,
        resourceName: String? = null,
        talukaId: Int? = null,
    ) = whereQueryAndParams(
        "r.type" to type,
        "r.resource_name" to resourceName,
        "r.taluka_id" to talukaId
    )
}