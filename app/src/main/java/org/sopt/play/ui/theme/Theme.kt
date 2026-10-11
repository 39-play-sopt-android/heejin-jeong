package org.sopt.play.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val PlayColorScheme = lightColorScheme(
    background = PlayColors.White,
    onBackground = PlayColors.Black
)

@Composable
fun PlaySoptTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = PlayColorScheme,
        typography = Typography,
        content = content
    )
}
