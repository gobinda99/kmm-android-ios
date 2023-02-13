package com.gobinda.kotlin.multiplatfom.mobile.sample

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

}

internal inline fun <reified T> Scope.getWith(vararg params: Any?): T {
    return get(parameters = { parametersOf(*params) })
}

// Simple function to clean up the syntax a bit
//fun KoinComponent.injectLogger(tag: String): Lazy<Logger> = inject { parametersOf(tag) }

expect val platformModule: Module
