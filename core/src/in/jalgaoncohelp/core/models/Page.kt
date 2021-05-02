package `in`.jalgaoncohelp.core.models

class Page(
    page: Long,
    val limit: Long
) {
    val offset: Long = page * limit
}
