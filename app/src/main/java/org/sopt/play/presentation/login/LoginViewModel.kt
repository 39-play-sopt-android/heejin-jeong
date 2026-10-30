package org.sopt.play.presentation.login

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import org.sopt.play.presentation.util.isValidEmail

class LoginViewModel : ViewModel() {
    val emailState = TextFieldState()
    val passwordState = TextFieldState()

    val emailError: String?
        get() {
            val email = emailState.text.toString()
            return if (email.isNotEmpty() && !isValidEmail(email)) {
                "올바른 이메일을 입력해주세요."
            } else {
                null
            }
        }

    val passwordError: String?
        get() {
            val password = passwordState.text
            return if (password.isNotEmpty() && password.length < 6) {
                "비밀번호는 6자 이상 입력해주세요."
            } else {
                null
            }
        }

    val canLogin: Boolean
        get() = isValidEmail(emailState.text.toString()) && passwordState.text.length >= 6
}
