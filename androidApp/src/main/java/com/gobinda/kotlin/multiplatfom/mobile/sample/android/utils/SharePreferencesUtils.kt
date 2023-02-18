package com.gobinda.kotlin.multiplatfom.mobile.sample.android.utils

import android.content.SharedPreferences

fun SharedPreferences.store(key :String, value : Boolean) = edit().putBoolean(key, value).apply()

fun SharedPreferences.store(key :String, value : String) = edit().putString(key, value).apply()
