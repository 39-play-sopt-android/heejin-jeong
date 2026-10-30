package org.sopt.play.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun loadProducts() {
        if (_uiState.value.products.isNotEmpty()) return
        _uiState.update {
            it.copy(products = HomeData.products)
        }
    }

    fun onMenuClick(index: Int) {
        _uiState.update {
            it.copy(selectedMenuIndex = index)
        }
    }

    fun onCategoryClick(index: Int) {
        _uiState.update {
            it.copy(selectedCategoryIndex = index)
        }
    }

}
