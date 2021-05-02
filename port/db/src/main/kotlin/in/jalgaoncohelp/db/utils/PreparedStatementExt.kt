package `in`.jalgaoncohelp.db.utils

import java.sql.PreparedStatement

fun PreparedStatement.withParams(params: List<Any?> = listOf()): PreparedStatement =
    this.also { self ->
        params.forEachIndexed { index, param -> self.setObject(index + 1, param) }
    }