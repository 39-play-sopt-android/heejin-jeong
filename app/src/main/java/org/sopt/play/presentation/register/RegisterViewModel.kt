package org.sopt.play.presentation.register

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import org.sopt.play.presentation.util.isValidEmail

class RegisterViewModel : ViewModel() {
    val nameState = TextFieldState()
    val emailState = TextFieldState()
    val passwordState = TextFieldState()
    val passwordConfirmState = TextFieldState()

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

    val confirmError: String?
        get() {
            val password = passwordState.text.toString()
            val passwordConfirm = passwordConfirmState.text.toString()
            return if (passwordConfirm.isNotEmpty() && passwordConfirm != password) {
                "비밀번호와 동일하게 입력해주세요."
            } else {
                null
            }
        }

    val canRegister: Boolean
        get() = nameState.text.isNotBlank() &&
            isValidEmail(emailState.text.toString()) &&
            passwordState.text.length >= 6 &&
            passwordConfirmState.text.toString() == passwordState.text.toString()
}
