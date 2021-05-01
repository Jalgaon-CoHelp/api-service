package `in`.jalgaoncohelp.db.adapter

import com.squareup.sqldelight.ColumnAdapter
import java.util.*

val uuidAdapter = object : ColumnAdapter<UUID, String> {
    override fun decode(databaseValue: String): UUID = UUID.fromString(databaseValue)
    override fun encode(value: UUID): String = value.toString()
}