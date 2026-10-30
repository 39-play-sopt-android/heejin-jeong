package org.sopt.play.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.R
import org.sopt.play.presentation.home.HomeData
import org.sopt.play.presentation.home.HomeUiState
import org.sopt.play.presentation.home.component.HomeBannerRow
import org.sopt.play.presentation.home.component.HomeCategoryChip
import org.sopt.play.presentation.home.component.HomeMenuItem
import org.sopt.play.presentation.home.component.HomeProductCard
import org.sopt.play.presentation.home.component.HomeTopBar
import org.sopt.play.ui.theme.PlayColors
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.PlayType

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onMenuClick: (Int) -> Unit,
    onCategoryClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val productRows = remember(uiState.products) { uiState.products.chunked(2) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(PlayColors.White)
            .safeDrawingPadding()
    ) {
        HomeTopBar(onSearchClick = {})

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(bottom = 96.dp)
        ) {
            item(key = "menus") {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .selectableGroup()
                ) {
                    HomeData.menus.forEachIndexed { index, menu ->
                        HomeMenuItem(
                            text = menu,
                            selected = index == uiState.selectedMenuIndex,
                            onClick = { onMenuClick(index) }
                        )
                    }
                }
            }

            item(key = "banners") {
                HomeBannerRow(banners = HomeData.banners)
            }

            item(key = "recommendation_title") {
                Text(
                    text = stringResource(R.string.home_recommendation_title),
                    style = PlayType.m18,
                    color = PlayColors.Black,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 20.dp,
                        end = 16.dp,
                        bottom = 12.dp
                    )
                )
            }

            item(key = "categories") {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .selectableGroup()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    HomeData.categories.forEachIndexed { index, category ->
                        HomeCategoryChip(
                            text = category,
                            selected = index == uiState.selectedCategoryIndex,
                            onClick = { onCategoryClick(index) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }

            itemsIndexed(
                items = productRows,
                key = { _, products -> products.first().imageRes }
            ) { index, products ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = if (index == 0) 0.dp else 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    products.forEach { product ->
                        HomeProductCard(
                            product = product,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    if (products.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Preview(
    name = "홈 · 360dp",
    showBackground = true,
    widthDp = 360,
    heightDp = 800
)
@Preview(
    name = "홈 · 412dp",
    showBackground = true,
    widthDp = 412,
    heightDp = 915
)
@Composable
private fun HomeScreenPreview() {
    PlaySoptTheme {
        HomeScreen(
            uiState = HomeUiState(products = HomeData.products),
            onMenuClick = {},
            onCategoryClick = {}
        )
    }
}
