package org.sopt.play.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    var selectedMenuIndex by mutableIntStateOf(1)
        private set

    var selectedCategoryIndex by mutableIntStateOf(0)
        private set

    val products = HomeData.products

    fun onMenuClick(index: Int) {
        selectedMenuIndex = index
    }

    fun onCategoryClick(index: Int) {
        selectedCategoryIndex = index
    }
}
