package org.sopt.play.presentation.home

import androidx.annotation.DrawableRes

data class HomeBanner(
    @param:DrawableRes
    @get:DrawableRes
    val imageRes: Int,
    val contentDescription: String
)
