package com.gobinda.kotlin.multiplatfom.mobile.sample.android

import android.app.Application
import android.content.Context
import android.util.Log
import com.gobinda.kotlin.multiplatfom.mobile.sample.initKoin
import updateShareModuleLog
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(
            module {
                single<Context> { this@App }
//                viewModel { BreedViewModel(get(), get { parametersOf("BreedViewModel") }) }
                single {
                    get<Context>().getSharedPreferences("sample.preference", Context.MODE_PRIVATE)
                }
                single {
                    { Log.i("Startup", "Hello from Android/Kotlin!") }
                }
            }
        )

        updateShareModuleLog(BuildConfig.DEBUG)
    }


}