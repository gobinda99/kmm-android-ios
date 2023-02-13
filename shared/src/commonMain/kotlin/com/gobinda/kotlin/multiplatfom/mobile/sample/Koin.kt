package com.gobinda.kotlin.multiplatfom.mobile.sample

//import co.touchlab.kermit.StaticConfig
//import co.touchlab.kermit.platformLogWriter
import client
import com.gobinda.kotlin.multiplatfom.mobile.sample.data.UserRepository
import com.gobinda.kotlin.multiplatfom.mobile.sample.data.UserRepositoryImpl
import com.gobinda.kotlin.multiplatfom.mobile.sample.data.source.local.sql.UserLocalDataSource
import com.gobinda.kotlin.multiplatfom.mobile.sample.db.SampleDb
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope
import org.koin.dsl.module
import kotlin.math.sin

fun initKoin(appModule: Module): KoinApplication {
    val koinApplication = startKoin {
        modules(
            appModule,
            platformModule,
            coreModule
        )
    }

    // Dummy initialization logic, making use of appModule declarations for demonstration purposes.
   /* val koin = koinApplication.koin
    // doOnStartup is a lambda which is implemented in Swift on iOS side
    val doOnStartup = koin.get<() -> Unit>()
    doOnStartup.invoke()

    val kermit = koin.get<Logger> { parametersOf(null) }
    // AppInfo is a Kotlin interface with separate Android and iOS implementations
    val appInfo = koin.get<AppInfo>()
    kermit.v { "App Id ${appInfo.appId}" }*/

    return koinApplication
}

private val coreModule = module {

    single { SampleDb(get()) }

    single<UserRepository> {
        val localData = UserLocalDataSource(get(), get())
        UserRepositoryImpl(localData = localData, remoteData = localData , get())  }

    single {
        client(get())
    }

    // platformLogWriter() is a relatively simple config option, useful for local debugging. For production
    // uses you *may* want to have a more robust configuration from the native platform. In KaMP Kit,
    // that would likely go into platformModule expect/actual.
    // See https://github.com/touchlab/Kermit
    /*val baseLogger = Logger(config = StaticConfig(logWriterList = listOf(platformLogWriter())), "KampKit")
    factory { (tag: String?) -> if (tag != null) baseLogger.withTag(tag) else baseLogger }*/

}

internal inline fun <reified T> Scope.getWith(vararg params: Any?): T {
    return get(parameters = { parametersOf(*params) })
}

// Simple function to clean up the syntax a bit
//fun KoinComponent.injectLogger(tag: String): Lazy<Logger> = inject { parametersOf(tag) }

expect val platformModule: Module
