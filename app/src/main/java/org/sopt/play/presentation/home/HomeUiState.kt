package org.sopt.play.presentation.home

import org.sopt.play.ui.model.HomeProduct

data class HomeUiState(
    val selectedMenuIndex: Int = 1,
    val selectedCategoryIndex: Int = 0,
    val menus: List<String> = emptyList(),
    val banners: List<HomeBanner> = emptyList(),
    val categories: List<String> = emptyList(),
    val products: List<HomeProduct> = emptyList()
)
