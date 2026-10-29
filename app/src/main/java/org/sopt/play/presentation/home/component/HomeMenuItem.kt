package org.sopt.play.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.modifier.noRippleSelectable
import org.sopt.play.ui.theme.PlayColors
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.PlayType

@Composable
fun HomeMenuItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .noRippleSelectable(
                selected = selected,
                role = Role.Tab,
                onClick = onClick
            )
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(4.dp)
                .background(
                    color = if (selected) PlayColors.Black else Color.Transparent,
                    shape = CircleShape
                )
        )

        Text(
            text = text,
            style = PlayType.m18,
            color = if (selected) PlayColors.Black else PlayColors.Gray3,
            maxLines = 1
        )
    }
}

@Preview(
    name = "메뉴 기본 · 선택",
    showBackground = true
)
@Composable
private fun HomeMenuItemPreview() {
    PlaySoptTheme {
        Row {
            HomeMenuItem(
                text = "2배적립",
                selected = false,
                onClick = {}
            )
            HomeMenuItem(
                text = "홈",
                selected = true,
                onClick = {}
            )
            HomeMenuItem(
                text = "셀렉티드",
                selected = false,
                onClick = {}
            )
        }
    }
}
