package org.sopt.play.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.sopt.play.R

private val Pretendard = FontFamily(
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_bold, FontWeight.Bold)
)

val Typography = Typography(
    headlineSmall = TextStyle(fontFamily = Pretendard, fontSize = 28.sp, fontWeight = FontWeight.Bold),
    bodyMedium = TextStyle(fontFamily = Pretendard, fontSize = 18.sp, fontWeight = FontWeight.Medium),
    labelLarge = TextStyle(fontFamily = Pretendard, fontSize = 16.sp, fontWeight = FontWeight.SemiBold),
    labelMedium = TextStyle(fontFamily = Pretendard, fontSize = 14.sp, fontWeight = FontWeight.Medium),
    labelSmall = TextStyle(fontFamily = Pretendard, fontSize = 12.sp, fontWeight = FontWeight.Medium)
)
