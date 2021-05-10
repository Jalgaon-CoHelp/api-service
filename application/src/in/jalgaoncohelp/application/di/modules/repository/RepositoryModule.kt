/*
 * MIT License
 *
 * Copyright (c) 2021 Jalgaon CoHelp
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package `in`.jalgaoncohelp.application.di.modules.repository

import `in`.jalgaoncohelp.application.di.modules.coroutine.IO_CONTEXT
import `in`.jalgaoncohelp.core.hospitals.HospitalRepository
import `in`.jalgaoncohelp.core.resource.ResourceRepository
import `in`.jalgaoncohelp.core.roles.RoleRepository
import `in`.jalgaoncohelp.core.taluka.TalukaRepository
import `in`.jalgaoncohelp.core.user.UserRepository
import `in`.jalgaoncohelp.core.userrole.UserRoleRepository
import `in`.jalgaoncohelp.db.hospitals.HospitalRepositoryImpl
import `in`.jalgaoncohelp.db.resource.ResourceRepositoryImpl
import `in`.jalgaoncohelp.db.role.RoleRepositoryImpl
import `in`.jalgaoncohelp.db.taluka.TalukaRepositoryImpl
import `in`.jalgaoncohelp.db.user.UserRepositoryImpl
import `in`.jalgaoncohelp.db.userrole.UserRoleRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun DI.MainBuilder.repositoryModule() {
    bindTalukaRepository()
    bindUserRepository()
    bindUserRoleRepository()
    bindHospitalRepository()
    bindResourceRepository()
    bindRoleRepository()
}

fun DI.MainBuilder.bindTalukaRepository() {
    bind<TalukaRepository>() with singleton { TalukaRepositoryImpl(instance(), instance(IO_CONTEXT)) }
}

fun DI.MainBuilder.bindUserRepository() {
    bind<UserRepository>() with singleton { UserRepositoryImpl(instance(), instance(IO_CONTEXT)) }
}

fun DI.MainBuilder.bindUserRoleRepository() {
    bind<UserRoleRepository>() with singleton { UserRoleRepositoryImpl(instance(), instance(IO_CONTEXT)) }
}

fun DI.MainBuilder.bindHospitalRepository() {
    bind<HospitalRepository>() with singleton { HospitalRepositoryImpl(instance(), instance(IO_CONTEXT)) }
}

fun DI.MainBuilder.bindResourceRepository() {
    bind<ResourceRepository>() with singleton { ResourceRepositoryImpl(instance(), instance(IO_CONTEXT)) }
}

fun DI.MainBuilder.bindRoleRepository() {
    bind<RoleRepository>() with singleton { RoleRepositoryImpl(instance(), instance(IO_CONTEXT)) }
}
