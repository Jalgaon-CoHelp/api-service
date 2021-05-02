package `in`.jalgaoncohelp.api.hospital

import `in`.jalgaoncohelp.api.utils.sendResponse
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.request.receiveParameters
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.HospitalRoute() {
    post("/new") {
        val controller by closestDI().instance<HospitalController>()
        val response = controller.addHospital(addHospitalRequest = call.receive())
        sendResponse(response)
    }

    get("/") {
        val controller by closestDI().instance<HospitalController>()
        val page = call.request.queryParameters["page"]?.toLong()
        val limit = call.request.queryParameters["limit"]?.toLong()
        val response = controller.getRecentlyUpdatedHospitals(page,limit)
        sendResponse(response)
    }
}