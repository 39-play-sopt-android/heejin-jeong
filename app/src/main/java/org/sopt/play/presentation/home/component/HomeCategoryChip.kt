package org.sopt.play.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.modifier.noRippleSelectable
import org.sopt.play.ui.theme.PlayColors
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.PlayType

@Composable
fun HomeCategoryChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(6.dp)

    Box(
        modifier = modifier
            .clip(shape)
            .background(if (selected) PlayColors.Gray5 else PlayColors.White)
            .border(
                width = 1.dp,
                color = if (selected) PlayColors.Gray5 else PlayColors.Gray2,
                shape = shape
            )
            .noRippleSelectable(
                selected = selected,
                role = Role.RadioButton,
                onClick = onClick
            )
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = PlayType.m14,
            color = if (selected) PlayColors.White else PlayColors.Gray5,
            maxLines = 1
        )
    }
}

@Preview(
    name = "카테고리 선택 · 기본 · 긴 이름",
    showBackground = true
)
@Composable
private fun HomeCategoryChipPreview() {
    PlaySoptTheme {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            HomeCategoryChip(
                text = "전체",
                selected = true,
                onClick = {}
            )
            HomeCategoryChip(
                text = "상의",
                selected = false,
                onClick = {}
            )
            HomeCategoryChip(
                text = "이너웨어/잠옷",
                selected = false,
                onClick = {}
            )
        }
    }
}
