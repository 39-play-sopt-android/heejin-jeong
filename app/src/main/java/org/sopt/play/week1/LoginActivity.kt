package org.sopt.play.week1

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.MainActivity
import org.sopt.play.ui.theme.PlayColors
import org.sopt.play.ui.theme.PlaySoptTheme

class LoginActivity : ComponentActivity() {
    private var registeredEmail: String? = null
    private var registeredPassword: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val registerLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                registeredEmail = result.data?.getStringExtra(RegisterActivity.EXTRA_EMAIL)
                registeredPassword = result.data?.getStringExtra(RegisterActivity.EXTRA_PASSWORD)
            }
        }

        setContent {
            PlaySoptTheme {
                LoginScreen(
                    onRegisterClick = {
                        registerLauncher.launch(Intent(this, RegisterActivity::class.java))
                    },
                    onLoginClick = { email, password ->
                        if (email == registeredEmail && password == registeredPassword) {
                            startActivity(
                                Intent(this, MainActivity::class.java).apply {
                                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                }
                            )
                        } else {
                            Toast.makeText(
                                this,
                                "이메일 또는 비밀번호가 올바르지 않아요.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                )
            }
        }
    }
}

internal fun isValidEmail(email: String): Boolean =
    email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()

@Composable
internal fun Week1TextField(
    title: String,
    state: TextFieldState,
    placeholder: String,
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

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = title, color = PlayColors.Black, style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(6.dp))
        if (isPassword) {
            OutlinedSecureTextField(
                state = state,
                modifier = fieldModifier,
                placeholder = { Text(placeholder, color = PlayColors.Gray2, style = MaterialTheme.typography.bodyMedium) },
                textStyle = MaterialTheme.typography.bodyMedium.copy(color = PlayColors.Black),
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
                placeholder = { Text(placeholder, color = PlayColors.Gray2, style = MaterialTheme.typography.bodyMedium) },
                textStyle = MaterialTheme.typography.bodyMedium.copy(color = PlayColors.Black),
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
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
        if (imeAction == ImeAction.Done) {
            Spacer(modifier = Modifier.size(1.dp).focusRequester(doneFocusRequester).focusable())
        }
    }
}

@Composable
fun LoginScreen(
    onRegisterClick: () -> Unit,
    onLoginClick: (String, String) -> Unit
) {
    val emailState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()
    val email = emailState.text.toString()
    val password = passwordState.text.toString()
    val emailError = if (email.isNotEmpty() && !isValidEmail(email)) {
        "올바른 이메일을 입력해주세요."
    } else null
    val passwordError = if (password.isNotEmpty() && password.length < 6) {
        "비밀번호는 6자 이상 입력해주세요."
    } else null
    val canLogin = isValidEmail(email) && password.length >= 6

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
            Spacer(modifier = Modifier.height(62.dp))
            Text(
                text = "이메일로 로그인하기",
                modifier = Modifier.fillMaxWidth(),
                color = PlayColors.Black,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(45.dp))
            Week1TextField(
                title = "이메일 주소",
                state = emailState,
                placeholder = "abc@email.com",
                errorMessage = emailError,
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(40.dp))
            Week1TextField(
                title = "비밀번호",
                state = passwordState,
                placeholder = "6자 이상의 비밀번호",
                errorMessage = passwordError,
                isPassword = true,
                imeAction = ImeAction.Done
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = { onLoginClick(email, password) },
                enabled = canLogin,
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PlayColors.Black,
                    contentColor = PlayColors.White,
                    disabledContainerColor = PlayColors.Gray1,
                    disabledContentColor = PlayColors.Gray3
                )
            ) {
                Text("로그인", style = MaterialTheme.typography.labelMedium)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("아직 계정이 없으신가요?", color = PlayColors.Gray3, style = MaterialTheme.typography.labelSmall)
                TextButton(onClick = onRegisterClick, contentPadding = PaddingValues(4.dp)) {
                    Text("회원가입하기", color = PlayColors.Black, style = MaterialTheme.typography.labelSmall)
                }
            }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 780)
@Composable
private fun LoginScreenPreview() {
    PlaySoptTheme {
        LoginScreen(onRegisterClick = {}, onLoginClick = { _, _ -> })
    }
}
