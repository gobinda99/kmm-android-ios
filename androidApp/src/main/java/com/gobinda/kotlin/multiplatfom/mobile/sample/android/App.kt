package com.gobinda.kotlin.multiplatfom.mobile.sample.android

import android.app.Application
import android.content.Context
import android.util.Log
import com.gobinda.kotlin.multiplatfom.mobile.sample.android.ui.auth.SignInViewModel
import com.gobinda.kotlin.multiplatfom.mobile.sample.android.ui.auth.SignUpViewModel
import com.gobinda.kotlin.multiplatfom.mobile.sample.initKoin
import initNapierLog
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(
            module {
                single<Context> { this@App }
                di()
            }
        )

        initNapierLog(BuildConfig.DEBUG)
    }

    private fun Module.di() {
        single {
            get<Context>().getSharedPreferences("sample.preference", MODE_PRIVATE)
        }
        single {
            get<Context>().resources
        }

        single {
            { Log.i("Startup", "Hello from Android/Kotlin!") }
        }
        viewModelOf(::SignInViewModel)
        viewModel {
            SignUpViewModel(get(), get())
        }
    }


}