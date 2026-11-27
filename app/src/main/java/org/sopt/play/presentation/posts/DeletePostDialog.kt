package org.sopt.play.presentation.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import org.sopt.play.ui.component.PlayButton
import org.sopt.play.ui.theme.PlayColors
import org.sopt.play.ui.theme.PlayType

@Composable
internal fun DeletePostDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .widthIn(max = 288.dp)
                .fillMaxWidth()
                .background(PlayColors.White, RoundedCornerShape(12.dp))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "게시글을 삭제할까요?",
                style = PlayType.m14.copy(fontSize = 16.sp),
                color = PlayColors.Gray6
            )
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                PlayButton(
                    text = "취소",
                    onClick = onDismiss,
                    enabled = true,
                    modifier = Modifier.weight(1f),
                    backgroundColor = PlayColors.Gray1,
                    textColor = PlayColors.Gray5
                )
                PlayButton(
                    text = "삭제",
                    onClick = onConfirm,
                    enabled = true,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
