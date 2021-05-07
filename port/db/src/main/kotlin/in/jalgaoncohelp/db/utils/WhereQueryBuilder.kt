package `in`.jalgaoncohelp.db.utils

typealias QueryAndParams = Pair<String, Array<Any>>

fun whereQueryAndParams(vararg columnValues: Pair<String, Any?>): QueryAndParams {
    val filteredColumnValues = columnValues.toList()
        .filter { (_, value) -> value != null }
        .map { (col, value) -> col to value }

    val whereCondition = if (filteredColumnValues.isNotEmpty()) {
        filteredColumnValues.joinToString(prefix = "WHERE ", separator = " AND ") { (col, _) -> "$col = ?" }
    } else ""

    val values: Array<Any> = filteredColumnValues.map { (_, value) -> value as Any }.toTypedArray()

    return whereCondition to values
}
