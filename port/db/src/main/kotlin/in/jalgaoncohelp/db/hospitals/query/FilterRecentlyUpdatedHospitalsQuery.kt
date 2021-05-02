package `in`.jalgaoncohelp.db.hospitals.query

import `in`.jalgaoncohelp.core.hospitals.model.BedType
import `in`.jalgaoncohelp.core.hospitals.model.Beds
import `in`.jalgaoncohelp.core.hospitals.model.Hospital
import `in`.jalgaoncohelp.core.models.Page
import `in`.jalgaoncohelp.core.taluka.model.Taluka
import `in`.jalgaoncohelp.db.utils.query
import javax.sql.DataSource

class FilterRecentlyUpdatedHospitalsQuery(private val dataSource: DataSource) {

    fun findRecentlyUpdatedHospitals(page: Page, talukaId: Int?, bedType: BedType?): List<Hospital> {
        val findQuery = findQuery(page, talukaId, bedType)

        val resultSet = dataSource.query(findQuery)
        val hospitals = mutableListOf<Hospital>()

        while (resultSet.next()) {
            val id = resultSet.getLong(1)
            val name = resultSet.getString(2)
            val address = resultSet.getString(3)
            val talId = resultSet.getLong(4).toInt()
            val talName = resultSet.getString(5)
            val contact1 = resultSet.getString(6)
            val contact2 = resultSet.getString(7)
            val bedGeneral = resultSet.getLong(8).toInt()
            val bedOxygen = resultSet.getLong(9).toInt()
            val bedIcu = resultSet.getLong(10).toInt()
            val bedVentilator = resultSet.getLong(11).toInt()
            val createdAt = resultSet.getString(12)!!
            val updatedAt = resultSet.getString(13)!!

            hospitals.add(
                Hospital(
                    id,
                    name,
                    address,
                    Taluka(talId, talName),
                    contact1,
                    contact2,
                    Beds(bedGeneral, bedOxygen, bedIcu, bedVentilator),
                    createdAt,
                    updatedAt
                )
            )
        }
        return hospitals
    }

    fun countRecentlyUpdatedHospitals(talukaId: Int?, bedType: BedType?): Long {
        val countQuery = countQuery(talukaId, bedType)
        val resultSet = dataSource.query(countQuery)

        resultSet.next()
        val count = resultSet.getLong(1)

        return count
    }

    private fun findQuery(page: Page, talukaId: Int? = null, bedType: BedType? = null) = buildString {
        val query = """
            SELECT h.id,
                   h.name,
                   h.address,
                   h.taluka_id,
                   t.name AS taluka_name,
                   h.contact_1,
                   h.contact_2,
                   h.bed_general,
                   h.bed_oxygen,
                   h.bed_icu,
                   h.bed_ventilator,
                   h.created_at,
                   h.updated_at
            FROM hospitals h
                     INNER JOIN talukas t ON t.id = h.taluka_id
            ${whereConditionQuery(talukaId, bedType)}
            ORDER BY updated_at DESC
            LIMIT ${page.limit} OFFSET ${page.offset};
        """.trimIndent()

        append(query)
    }

    private fun countQuery(talukaId: Int? = null, bedType: BedType? = null) = buildString {
        val query = """
            SELECT count(*) AS total_hospitals
            FROM hospitals h
            ${whereConditionQuery(talukaId, bedType)};
        """.trimIndent()
        append(query)
    }

    private fun whereConditionQuery(talukaId: Int?, bedType: BedType?) = buildString {
        if (talukaId != null || bedType != null) {
            append("\nWHERE ")
        }
        val talukaCondition = if (talukaId != null) {
            """
                h.taluka_id = $talukaId
            """.trimIndent()
        } else null

        talukaCondition?.let { append(talukaCondition) }

        val bedCondition = if (bedType != null) {
            val columnName = when (bedType) {
                BedType.General -> "bed_general"
                BedType.Oxygen -> "bed_oxygen"
                BedType.ICU -> "bed_icu"
                BedType.Ventilator -> "bed_ventilator"
            }
            """
                h.$columnName > 0
            """.trimIndent()
        } else null

        bedCondition?.let {
            if (talukaCondition != null) {
                append(" AND ")
            }
            append(bedCondition)
        }
    }
}

