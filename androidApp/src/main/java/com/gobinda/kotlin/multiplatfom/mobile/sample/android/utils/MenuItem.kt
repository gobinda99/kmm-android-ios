package com.gobinda.kotlin.multiplatfom.mobile.sample.android.utils

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val id: String,
    val title: String? = null,
    val icon:  ImageVector? = null
)