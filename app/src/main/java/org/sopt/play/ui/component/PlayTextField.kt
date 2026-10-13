package org.sopt.play.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextFieldDecorator
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.theme.PlayColors
import org.sopt.play.ui.theme.PlayType

@Composable
fun PlayTextField(
    title: String,
    state: TextFieldState,
    placeholder: String,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    isPassword: Boolean = false,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val shape = RoundedCornerShape(12.dp)
    val borderColor = when {
        errorMessage != null -> PlayColors.Red
        isFocused -> PlayColors.Gray5
        else -> PlayColors.Gray2
    }
    val cursorBrush = SolidColor(
        if (errorMessage != null) PlayColors.Red else PlayColors.Gray5
    )
    val keyboardOptions = KeyboardOptions(
        keyboardType = if (isPassword) KeyboardType.Password else keyboardType,
        imeAction = imeAction
    )
    val onKeyboardAction = {
        if (imeAction == ImeAction.Done) {
            focusManager.clearFocus(force = true)
            keyboardController?.hide()
        } else {
            focusManager.moveFocus(FocusDirection.Next)
        }
    }
    val fieldModifier = Modifier
        .fillMaxWidth()
        .semantics {
            contentDescription = title
            if (errorMessage != null) {
                error(errorMessage)
            }
        }
        .onPreviewKeyEvent { event ->
            if (imeAction == ImeAction.Done && event.key == Key.Enter) {
                if (event.type == KeyEventType.KeyDown) {
                    onKeyboardAction()
                }
                true
            } else {
                false
            }
        }
    val decorator = TextFieldDecorator { innerTextField ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = PlayColors.White,
                    shape = shape
                )
                .border(
                    width = 2.dp,
                    color = borderColor,
                    shape = shape
                )
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            if (state.text.isEmpty()) {
                Text(
                    text = placeholder,
                    color = PlayColors.Gray2,
                    style = PlayType.m18,
                    maxLines = 1
                )
            }
            innerTextField()
        }
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            modifier = Modifier.padding(start = 8.dp),
            color = PlayColors.Gray6,
            style = PlayType.sb16
        )

        Spacer(modifier = Modifier.height(6.dp))

        if (isPassword) {
            BasicSecureTextField(
                state = state,
                modifier = fieldModifier,
                textStyle = PlayType.m18.copy(color = PlayColors.Gray6),
                textObfuscationMode = TextObfuscationMode.Hidden,
                keyboardOptions = keyboardOptions,
                onKeyboardAction = { onKeyboardAction() },
                interactionSource = interactionSource,
                cursorBrush = cursorBrush,
                decorator = decorator
            )
        } else {
            BasicTextField(
                state = state,
                modifier = fieldModifier,
                textStyle = PlayType.m18.copy(color = PlayColors.Gray5),
                keyboardOptions = keyboardOptions,
                onKeyboardAction = { onKeyboardAction() },
                lineLimits = TextFieldLineLimits.SingleLine,
                interactionSource = interactionSource,
                cursorBrush = cursorBrush,
                decorator = decorator
            )
        }
        AnimatedContent(
            targetState = errorMessage,
            transitionSpec = {
                (expandVertically() + fadeIn()).togetherWith(shrinkVertically() + fadeOut())
            },
            label = "입력 오류"
        ) { message ->
            if (message != null) {
                Text(
                    text = message,
                    modifier = Modifier.padding(start = 8.dp, top = 6.dp),
                    color = PlayColors.Red,
                    style = PlayType.m14
                )
            }
        }
    }
}
