package com.gobinda.kotlin.multiplatfom.mobile.sample

import com.gobinda.kotlin.multiplatfom.mobile.sample.db.SampleDb
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.engine.okhttp.*
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            SampleDb.Schema,
            get(),
            "SampleDb"
        )
    }

   single<Settings> {
       SharedPreferencesSettings(get()) }

    single {
        OkHttp.create()
    }
}
