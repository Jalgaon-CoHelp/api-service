
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
package `in`.jalgaoncohelp.application.di.modules.datasource

import `in`.jalgaoncohelp.application.config.DatabaseConfig
import `in`.jalgaoncohelp.db.DefaultDataSource
import `in`.jalgaoncohelp.db.JalgaonCoHelpDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.asJdbcDriver
import javax.sql.DataSource
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun DI.MainBuilder.dataSourceModule() {
    bindDataSource()
    bindScalefleetDb()
}

private fun DI.MainBuilder.bindDataSource() {
    bind<DataSource>() with singleton {
        val databaseConfig = instance<DatabaseConfig>()
        with(databaseConfig) { DefaultDataSource("jdbc:postgresql://$host:$port/$database", user, password) }
    }
}

private fun DI.MainBuilder.bindScalefleetDb() {
    bind<SqlDriver>() with singleton { instance<DataSource>().asJdbcDriver() }
    bind<JalgaonCoHelpDatabase>() with singleton { JalgaonCoHelpDatabase(instance()) }
}
