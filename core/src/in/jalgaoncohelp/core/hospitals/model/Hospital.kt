package `in`.jalgaoncohelp.core.hospitals.model

import `in`.jalgaoncohelp.core.taluka.model.Taluka
import `in`.jalgaoncohelp.core.utils.epochMillis

data class Hospital(
    val id: Long,
    val name: String,
    val address: String?,
    val taluka: Taluka,
    val contact1: String?,
    val contact2: String?,
    val beds: Beds,
    val createdAt: String,
    val updatedAt: String
) {
    val createdAtMillis: Long get() = epochMillis(createdAt)
    val updatedAtMillis: Long get() = epochMillis(updatedAt)
}