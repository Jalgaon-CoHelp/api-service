package `in`.jalgaoncohelp.application.di.modules.datasource.adapters

import `in`.jalgaoncohelp.db.adapter.uuidAdapter
import `in`.jalgaoncohelp.sql.users.Users
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

fun DI.MainBuilder.adaptersModule() {
//    bindUsersAdapter()
}

fun DI.MainBuilder.bindUsersAdapter() {
//    bind<Users.Adapter>() with singleton { Users.Adapter(idAdapter = uuidAdapter) }
}