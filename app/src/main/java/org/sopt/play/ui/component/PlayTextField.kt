package org.sopt.play.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
    val doneFocusRequester = remember { FocusRequester() }
    val keyboardOptions = KeyboardOptions(
        keyboardType = if (isPassword) KeyboardType.Password else keyboardType,
        imeAction = imeAction
    )
    val onKeyboardAction = {
        if (imeAction == ImeAction.Done) {
            doneFocusRequester.requestFocus()
            keyboardController?.hide()
        } else {
            focusManager.moveFocus(FocusDirection.Next)
        }
    }
    val fieldModifier = Modifier.fillMaxWidth().then(
        if (imeAction == ImeAction.Done) {
            Modifier.onPreviewKeyEvent { event ->
                if (event.key == Key.Enter) {
                    if (event.type == KeyEventType.KeyDown) onKeyboardAction()
                    true
                } else false
            }
        } else Modifier
    )
    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = PlayColors.Gray5,
        unfocusedBorderColor = PlayColors.Gray2,
        errorBorderColor = PlayColors.Red,
        cursorColor = PlayColors.Gray5,
        errorCursorColor = PlayColors.Red,
        focusedContainerColor = PlayColors.White,
        unfocusedContainerColor = PlayColors.White,
        errorContainerColor = PlayColors.White
    )

    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = title, color = PlayColors.Black, style = PlayType.sb16)

        Spacer(modifier = Modifier.height(6.dp))

        if (isPassword) {
            OutlinedSecureTextField(
                state = state,
                modifier = fieldModifier,
                placeholder = { Text(placeholder, color = PlayColors.Gray2, style = PlayType.m18) },
                textStyle = PlayType.m18.copy(color = PlayColors.Black),
                isError = errorMessage != null,
                textObfuscationMode = TextObfuscationMode.Hidden,
                keyboardOptions = keyboardOptions,
                onKeyboardAction = { onKeyboardAction() },
                shape = RoundedCornerShape(10.dp),
                colors = fieldColors
            )
        } else {
            OutlinedTextField(
                state = state,
                modifier = fieldModifier,
                placeholder = { Text(placeholder, color = PlayColors.Gray2, style = PlayType.m18) },
                textStyle = PlayType.m18.copy(color = PlayColors.Black),
                isError = errorMessage != null,
                keyboardOptions = keyboardOptions,
                onKeyboardAction = { onKeyboardAction() },
                lineLimits = TextFieldLineLimits.SingleLine,
                shape = RoundedCornerShape(10.dp),
                colors = fieldColors
            )
        }
        AnimatedContent(
            targetState = errorMessage,
            transitionSpec = {
                (expandVertically() + fadeIn()).togetherWith(shrinkVertically() + fadeOut())
            },
            label = "입력 오류"
        ) {
            if (it != null) {
                Text(
                    text = it,
                    modifier = Modifier.padding(start = 4.dp, top = 5.dp),
                    color = PlayColors.Red,
                    style = PlayType.m14
                )
            }
        }
        if (imeAction == ImeAction.Done) {
            Spacer(modifier = Modifier.size(1.dp).focusRequester(doneFocusRequester).focusable())
        }
    }
}
