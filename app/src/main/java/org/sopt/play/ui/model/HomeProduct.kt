package org.sopt.play.ui.model

import androidx.annotation.DrawableRes

data class HomeProduct(
    @param:DrawableRes
    @get:DrawableRes
    val imageRes: Int,
    val storeName: String,
    val name: String,
    val discountRate: Int,
    val price: Int,
    val isFavorite: Boolean = false
)
