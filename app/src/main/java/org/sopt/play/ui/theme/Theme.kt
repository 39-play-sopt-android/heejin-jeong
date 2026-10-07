package org.sopt.play.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val PlayColorScheme = lightColorScheme(
    primary = PlayColors.Black,
    onPrimary = PlayColors.White,
    background = PlayColors.White,
    onBackground = PlayColors.Black,
    surface = PlayColors.White,
    onSurface = PlayColors.Black,
    outline = PlayColors.Gray2,
    error = PlayColors.Red
)

@Composable
fun PlaySoptTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = PlayColorScheme,
        typography = Typography,
        content = content
    )
}
