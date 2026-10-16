package org.sopt.play.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import java.util.Locale
import org.sopt.play.R
import org.sopt.play.presentation.home.HomeData
import org.sopt.play.presentation.home.HomeProduct
import org.sopt.play.ui.theme.PlayColors
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.PlayType

@Composable
fun HomeProductCard(
    product: HomeProduct,
    modifier: Modifier = Modifier
) {
    val price = remember(product.price) {
        NumberFormat.getIntegerInstance(Locale.KOREA).format(product.price)
    }

    Column(
        modifier = modifier.background(PlayColors.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(176f / 220f)
        ) {
            Image(
                painter = painterResource(product.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Icon(
                painter = painterResource(
                    if (product.isFavorite) {
                        R.drawable.ic_favorite_filled
                    } else {
                        R.drawable.ic_favorite_outline
                    }
                ),
                contentDescription = stringResource(
                    if (product.isFavorite) {
                        R.string.home_favorite_selected_description
                    } else {
                        R.string.home_favorite_unselected_description
                    }
                ),
                tint = if (product.isFavorite) PlayColors.Red else PlayColors.White,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .size(24.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    top = 12.dp,
                    end = 8.dp,
                    bottom = 20.dp
                ),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = product.storeName,
                style = PlayType.m14,
                color = PlayColors.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = product.name,
                style = PlayType.r14,
                color = PlayColors.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = stringResource(
                        id = R.string.home_discount_rate,
                        formatArgs = arrayOf(product.discountRate)
                    ),
                    style = PlayType.m14,
                    color = PlayColors.Red,
                    maxLines = 1
                )

                Text(
                    text = price,
                    style = PlayType.m14,
                    color = PlayColors.Black,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview(
    name = "상품 2열 · 360dp",
    showBackground = true,
    widthDp = 360
)
@Preview(
    name = "상품 2열 · 412dp",
    showBackground = true,
    widthDp = 412
)
@Composable
private fun HomeProductCardPreview() {
    PlaySoptTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            HomeProductCard(
                product = HomeData.products[0],
                modifier = Modifier.weight(1f)
            )
            HomeProductCard(
                product = HomeData.products[1],
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(
    name = "긴 스토어명 · 상품명",
    showBackground = true,
    widthDp = 176
)
@Composable
private fun HomeProductCardLongTextPreview() {
    PlaySoptTheme {
        HomeProductCard(
            product = HomeData.products[0].copy(
                storeName = "한 줄을 넘는 길이의 스토어 이름",
                name = "한 줄을 넘는 길이의 상품 이름을 말줄임으로 표시합니다"
            )
        )
    }
}
