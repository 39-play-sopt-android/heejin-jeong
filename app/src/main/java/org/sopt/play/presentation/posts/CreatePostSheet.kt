package org.sopt.play.presentation.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.component.PlayButton
import org.sopt.play.ui.component.PlayTextField
import org.sopt.play.ui.theme.PlayColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CreatePostSheet(onDismiss: () -> Unit, onSubmit: (String, String) -> Unit) {
    val title = rememberTextFieldState()
    val content = rememberTextFieldState()
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = PlayColors.White,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        dragHandle = {
            Spacer(
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 20.dp)
                    .width(36.dp)
                    .height(4.dp)
                    .background(PlayColors.Gray3, RoundedCornerShape(2.dp))
            )
        },
        scrimColor = Color.Black.copy(alpha = 0.4f)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            PlayTextField(
                title = "제목",
                state = title,
                placeholder = "제목을 입력하세요.",
                inputTransformation = InputTransformation.maxLength(20)
            )
            Spacer(Modifier.height(12.dp))
            PlayTextField(
                title = "본문",
                state = content,
                placeholder = "본문을 입력하세요.",
                inputTransformation = InputTransformation.maxLength(1000),
                lineLimits = TextFieldLineLimits.MultiLine(maxHeightInLines = 5),
                imeAction = ImeAction.Default
            )
            Spacer(Modifier.height(20.dp))
            PlayButton(
                text = "완료",
                enabled = title.text.isNotBlank() && content.text.isNotBlank(),
                onClick = { onSubmit(title.text.toString(), content.text.toString()) }
            )
        }
    }
}
