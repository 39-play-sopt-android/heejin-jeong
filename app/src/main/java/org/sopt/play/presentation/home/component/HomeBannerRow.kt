package org.sopt.play.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.presentation.home.HomeBanner
import org.sopt.play.presentation.home.HomeData
import org.sopt.play.ui.theme.PlaySoptTheme

@Composable
fun HomeBannerRow(
    banners: List<HomeBanner>,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxWidth()
    ) {
        val bannerWidth = (maxWidth - 32.dp).coerceAtLeast(1.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            banners.forEach { banner ->
                Image(
                    painter = painterResource(banner.imageRes),
                    contentDescription = banner.contentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(bannerWidth)
                        .aspectRatio(328f / 195f)
                )
            }
        }
    }
}

@Preview(
    name = "배너 · 360dp",
    showBackground = true,
    widthDp = 360
)
@Composable
private fun HomeBannerRowPreview() {
    PlaySoptTheme {
        HomeBannerRow(banners = HomeData.banners)
    }
}
