package org.sopt.play.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.component.PlayButton
import org.sopt.play.ui.component.PlayTextField
import org.sopt.play.ui.theme.PlayColors
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.PlayType

@Composable
fun RegisterScreen(
    nameState: TextFieldState,
    emailState: TextFieldState,
    passwordState: TextFieldState,
    passwordConfirmState: TextFieldState,
    emailError: String?,
    passwordError: String?,
    confirmError: String?,
    canRegister: Boolean,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(PlayColors.White)
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "이메일로 회원가입",
            modifier = Modifier.fillMaxWidth(),
            color = PlayColors.Black,
            style = PlayType.b28
        )

        Spacer(modifier = Modifier.height(40.dp))

        PlayTextField(
            title = "이름",
            state = nameState,
            placeholder = "홍길동"
        )

        Spacer(modifier = Modifier.height(32.dp))

        PlayTextField(
            title = "이메일 주소",
            state = emailState,
            placeholder = "abc@email.com",
            errorMessage = emailError,
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(32.dp))

        PlayTextField(
            title = "비밀번호",
            state = passwordState,
            placeholder = "6자 이상의 비밀번호",
            errorMessage = passwordError,
            isPassword = true
        )

        Spacer(modifier = Modifier.height(32.dp))

        PlayTextField(
            title = "비밀번호 확인",
            state = passwordConfirmState,
            placeholder = "6자 이상의 비밀번호",
            errorMessage = confirmError,
            isPassword = true,
            imeAction = ImeAction.Done
        )

        Spacer(modifier = Modifier.height(40.dp))

        PlayButton(
            text = "회원가입",
            onClick = onRegisterClick,
            enabled = canRegister
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 780
)
@Composable
private fun RegisterScreenPreview() {
    PlaySoptTheme {
        RegisterScreen(
            nameState = rememberTextFieldState(),
            emailState = rememberTextFieldState(),
            passwordState = rememberTextFieldState(),
            passwordConfirmState = rememberTextFieldState(),
            emailError = null,
            passwordError = null,
            confirmError = null,
            canRegister = false,
            onRegisterClick = {}
        )
    }
}
