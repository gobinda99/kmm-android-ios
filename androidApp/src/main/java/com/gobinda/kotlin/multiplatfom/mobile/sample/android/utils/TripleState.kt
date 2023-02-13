package com.gobinda.kotlin.multiplatfom.mobile.sample.android.utils

import com.gobinda.kotlin.multiplatfom.mobile.sample.android.R

data class TripleState<T>(var value : T, var isError: Boolean = false, var message : Int = R.string.error)
