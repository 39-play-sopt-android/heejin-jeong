package org.sopt.play.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import org.sopt.play.R

private val Pretendard = FontFamily(
    Font(
        resId = R.font.pretendard_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.pretendard_medium,
        weight = FontWeight.Medium
    ),
    Font(
        resId = R.font.pretendard_semibold,
        weight = FontWeight.SemiBold
    ),
    Font(
        resId = R.font.pretendard_bold,
        weight = FontWeight.Bold
    )
)

object PlayType {
    val r14 = TextStyle(
        fontFamily = Pretendard,
        fontSize = 14.sp,
        lineHeight = 1.2.em,
        letterSpacing = (-0.01).em,
        fontWeight = FontWeight.Normal
    )

    val b28 = TextStyle(
        fontFamily = Pretendard,
        fontSize = 28.sp,
        lineHeight = 1.2.em,
        letterSpacing = (-0.01).em,
        fontWeight = FontWeight.Bold
    )

    val m18 = TextStyle(
        fontFamily = Pretendard,
        fontSize = 18.sp,
        lineHeight = 1.2.em,
        letterSpacing = (-0.01).em,
        fontWeight = FontWeight.Medium
    )

    val sb16 = TextStyle(
        fontFamily = Pretendard,
        fontSize = 16.sp,
        lineHeight = 1.2.em,
        letterSpacing = (-0.01).em,
        fontWeight = FontWeight.SemiBold
    )

    val m14 = TextStyle(
        fontFamily = Pretendard,
        fontSize = 14.sp,
        lineHeight = 1.2.em,
        letterSpacing = (-0.01).em,
        fontWeight = FontWeight.Medium
    )

    val sb14 = TextStyle(
        fontFamily = Pretendard,
        fontSize = 14.sp,
        lineHeight = 1.2.em,
        letterSpacing = (-0.01).em,
        fontWeight = FontWeight.SemiBold
    )
}
