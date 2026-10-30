package org.sopt.play.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.play.presentation.HomeScreen

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadProducts()
    }

    HomeScreen(
        uiState = uiState,
        onMenuClick = viewModel::onMenuClick,
        onCategoryClick = viewModel::onCategoryClick,
        onFavoriteClick = viewModel::onFavoriteClick,
        modifier = modifier
    )
}
