package com.gobinda.kotlin.multiplatfom.mobile.sample.android

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.util.Log
import com.gobinda.kotlin.multiplatfom.mobile.sample.android.ui.auth.SignInViewModel
import com.gobinda.kotlin.multiplatfom.mobile.sample.android.ui.auth.SignUpViewModel
import com.gobinda.kotlin.multiplatfom.mobile.sample.initKoin
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import updateShareModuleLog
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(
            module {
                single<Context> { this@App }

                single {
                    get<Context>().getSharedPreferences("sample.preference", Context.MODE_PRIVATE)
                }
                single{
                    get<Context>().resources
                }
                single {
                    { Log.i("Startup", "Hello from Android/Kotlin!") }
                }
              /*  viewModel {
                    SignInViewModel(get(), get(), get())
                }*/
                viewModelOf(::SignInViewModel)
                viewModel {
                    SignUpViewModel(get(), get())
                }

            }
        )

        updateShareModuleLog(BuildConfig.DEBUG)
    }


}