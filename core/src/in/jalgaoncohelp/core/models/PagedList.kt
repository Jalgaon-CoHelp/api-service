package `in`.jalgaoncohelp.core.models

import kotlin.math.ceil

class PagedList<T>(val records: Long, limit: Long, val list: List<T>) {
    val pages = if (records == 0L || limit == 0L) 0L else ceil(records / (limit.toDouble())).toLong()
}