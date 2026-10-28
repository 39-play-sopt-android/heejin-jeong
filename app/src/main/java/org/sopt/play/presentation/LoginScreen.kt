package org.sopt.play.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.presentation.util.isValidEmail
import org.sopt.play.ui.component.PlayButton
import org.sopt.play.ui.component.PlayTextField
import org.sopt.play.ui.modifier.noRippleClickable
import org.sopt.play.ui.theme.PlayColors
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.PlayType

@Composable
fun LoginScreen(
    onRegisterClick: () -> Unit,
    onLoginClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val emailState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()

    val email = emailState.text.toString()
    val password = passwordState.text.toString()

    val emailError = if (email.isNotEmpty() && !isValidEmail(email)) {
        "올바른 이메일을 입력해주세요."
    } else {
        null
    }

    val passwordError = if (password.isNotEmpty() && password.length < 6) {
        "비밀번호는 6자 이상 입력해주세요."
    } else {
        null
    }

    val canLogin = isValidEmail(email) && password.length >= 6

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
            text = "이메일로 로그인하기",
            modifier = Modifier.fillMaxWidth(),
            color = PlayColors.Black,
            style = PlayType.b28
        )

        Spacer(modifier = Modifier.height(40.dp))

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
            isPassword = true,
            imeAction = ImeAction.Done
        )

        Spacer(modifier = Modifier.height(40.dp))

        PlayButton(
            text = "로그인",
            onClick = {
                onLoginClick(
                    email,
                    password
                )
            },
            enabled = canLogin
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "아직 계정이 없으신가요?",
                color = PlayColors.Gray3,
                style = PlayType.m14
            )

            Text(
                text = "회원가입하기",
                modifier = Modifier.noRippleClickable(
                    role = Role.Button,
                    onClick = onRegisterClick
                ),
                color = PlayColors.Gray6,
                style = PlayType.m14
            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 800
)
@Composable
private fun LoginScreenPreview() {
    PlaySoptTheme {
        LoginScreen(
            onRegisterClick = {},
            onLoginClick = { _, _ -> }
        )
    }
}
