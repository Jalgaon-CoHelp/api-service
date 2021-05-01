package `in`.jalgaoncohelp.application.di.modules.interactors

import `in`.jalgaoncohelp.core.volunteer.AddVolunteer
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun DI.MainBuilder.interactorsModule() {
    bindAddVolunteer()
}

fun DI.MainBuilder.bindAddVolunteer() {
    bind<AddVolunteer>() with singleton { AddVolunteer(instance(), instance(), instance(), instance()) }
}
