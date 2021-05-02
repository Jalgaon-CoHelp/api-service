package `in`.jalgaoncohelp.core.utils

import java.time.OffsetDateTime

fun epochMillis(timestamp: String): Long = OffsetDateTime.parse(timestamp.replace(" ", "T")).toInstant().toEpochMilli()