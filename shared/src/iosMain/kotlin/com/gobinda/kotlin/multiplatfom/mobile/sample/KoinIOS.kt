package com.gobinda.kotlin.multiplatfom.mobile.sample

import com.gobinda.kotlin.multiplatfom.mobile.sample.data.UserRepository
import com.gobinda.kotlin.multiplatfom.mobile.sample.db.SampleDb
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import io.ktor.client.engine.darwin.*
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

fun initKoinIos(
    userDefaults: NSUserDefaults,
    doOnStartup: () -> Unit
): KoinApplication = initKoin(
    module {
        single<Settings> { NSUserDefaultsSettings(userDefaults) }
//        single { appInfo }
        single { doOnStartup }
    }
)

actual val platformModule = module {
    single<SqlDriver> { NativeSqliteDriver(SampleDb.Schema, "SampleDb") }

    single {
        Dispatchers.Default
    }

    single { Darwin.create() }

//    single { BreedCallbackViewModel(get(), getWith("BreedCallbackViewModel")) }
}

//// Access from Swift to create a logger
//@Suppress("unused")
//fun Koin.loggerWithTag(tag: String) =
//    get<Logger>(qualifier = null) { parametersOf(tag) }
//
@Suppress("unused") // Called from Swift
object KotlinDependencies : KoinComponent {
    fun getUserRepo() : UserRepository = getKoin().get()
}
