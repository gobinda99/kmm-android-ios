package com.gobinda.kotlin.multiplatfom.mobile.sample


interface Platform {
    val name: String
}

expect fun getPlatform(): Platform


