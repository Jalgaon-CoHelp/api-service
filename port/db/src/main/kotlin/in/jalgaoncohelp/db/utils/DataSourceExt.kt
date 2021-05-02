package `in`.jalgaoncohelp.db.utils

import java.sql.Connection
import java.sql.ResultSet
import javax.sql.DataSource

fun Connection.executeQuery(sql: String, params: List<Any> = listOf()): ResultSet =
    this.prepareStatement(sql).withParams(params.toList()).executeQuery()

fun DataSource.query(sql: String, vararg params: Any) = this.connection.use { it.executeQuery(sql, params.toList()) }