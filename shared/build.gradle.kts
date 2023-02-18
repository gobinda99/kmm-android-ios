plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // key:value store
                implementation("com.russhwolf:multiplatform-settings:1.0.0")
                // di
                implementation("io.insert-koin:koin-core:3.3.2")
                // log
                implementation("io.github.aakira:napier:2.6.1")
                // coroutine
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

                // Networking
                implementation("io.ktor:ktor-client-core:2.2.3")
                implementation("io.ktor:ktor-client-logging:2.2.3")

                // Database sqldelite
                implementation("com.squareup.sqldelight:coroutines-extensions:1.5.5")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies{
                implementation("io.ktor:ktor-client-okhttp:2.2.3")
                implementation("com.squareup.sqldelight:android-driver:1.5.5")
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies{
                implementation("io.ktor:ktor-client-darwin:2.2.3")
                implementation("com.squareup.sqldelight:native-driver:1.5.5")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.gobinda.kotlin.multiplatfom.mobile.sample"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}

sqldelight {
    database("SampleDb") {
        packageName = "com.gobinda.kotlin.multiplatfom.mobile.sample.db"
    }
}