package `in`.jalgaoncohelp.core.resources

import `in`.jalgaoncohelp.core.exception.notFoundError
import `in`.jalgaoncohelp.core.resources.model.Resource

class ResourceService {

    val resources: List<Resource> get() = Resource.values().toList()

    fun findResourceById(id: Int): Resource {
        return Resource.values().find { it.id == id } ?: notFoundError("Resource not exists with this ID")
    }

    fun findResourceByName(name: String): Resource {
        return Resource.values().find { it.name == name } ?: notFoundError("Resource not exists with this name")
    }
}