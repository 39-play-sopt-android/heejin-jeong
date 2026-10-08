package org.sopt.play.week1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.theme.PlayColors
import org.sopt.play.ui.theme.PlaySoptTheme

class RegisterActivity : ComponentActivity() {
    companion object {
        const val EXTRA_EMAIL = "org.sopt.play.week1.EMAIL"
        const val EXTRA_PASSWORD = "org.sopt.play.week1.PASSWORD"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaySoptTheme {
                RegisterScreen { email, password ->
                    val result = Intent()
                        .putExtra(EXTRA_EMAIL, email)
                        .putExtra(EXTRA_PASSWORD, password)
                    setResult(RESULT_OK, result)
                    finish()
                }
            }
        }
    }
}

@Composable
fun RegisterScreen(onRegisterClick: (String, String) -> Unit) {
    val nameState = rememberTextFieldState()
    val emailState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()
    val passwordConfirmState = rememberTextFieldState()
    val name = nameState.text.toString()
    val email = emailState.text.toString()
    val password = passwordState.text.toString()
    val passwordConfirm = passwordConfirmState.text.toString()
    val emailError = if (email.isNotEmpty() && !isValidEmail(email)) {
        "올바른 이메일을 입력해주세요."
    } else null
    val passwordError = if (password.isNotEmpty() && password.length < 6) {
        "비밀번호는 6자 이상 입력해주세요."
    } else null
    val confirmError = if (passwordConfirm.isNotEmpty() && passwordConfirm != password) {
        "비밀번호와 동일하게 입력해주세요."
    } else null
    val canRegister = name.isNotBlank() && isValidEmail(email) &&
        password.length >= 6 &&
        passwordConfirm.isNotEmpty() &&
        passwordConfirm == password

    CompositionLocalProvider(LocalRippleConfiguration provides null) {
        Scaffold(containerColor = PlayColors.White) { innerPadding ->
            Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(PlayColors.White)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Spacer(modifier = Modifier.height(74.dp))
            Text(
                text = "이메일로 회원가입",
                modifier = Modifier.fillMaxWidth(),
                color = PlayColors.Black,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(45.dp))
            Week1TextField(
                title = "이름",
                state = nameState,
                placeholder = "홍길동"
            )
            Spacer(modifier = Modifier.height(38.dp))
            Week1TextField(
                title = "이메일 주소",
                state = emailState,
                placeholder = "abc@email.com",
                errorMessage = emailError,
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(38.dp))
            Week1TextField(
                title = "비밀번호",
                state = passwordState,
                placeholder = "6자 이상의 비밀번호",
                errorMessage = passwordError,
                isPassword = true
            )
            Spacer(modifier = Modifier.height(38.dp))
            Week1TextField(
                title = "비밀번호 확인",
                state = passwordConfirmState,
                placeholder = "6자 이상의 비밀번호",
                errorMessage = confirmError,
                isPassword = true,
                imeAction = ImeAction.Done
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = { onRegisterClick(email, password) },
                enabled = canRegister,
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PlayColors.Black,
                    contentColor = PlayColors.White,
                    disabledContainerColor = PlayColors.Gray1,
                    disabledContentColor = PlayColors.Gray3
                )
            ) {
                Text("회원가입", style = MaterialTheme.typography.labelMedium)
            }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 780)
@Composable
private fun RegisterScreenPreview() {
    PlaySoptTheme {
        RegisterScreen(onRegisterClick = { _, _ -> })
    }
}
