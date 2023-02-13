package com.gobinda.kotlin.multiplatfom.mobile.sample

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}