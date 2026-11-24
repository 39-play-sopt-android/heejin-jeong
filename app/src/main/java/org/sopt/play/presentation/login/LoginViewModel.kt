package org.sopt.play.presentation.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.sopt.play.data.AuthRepository

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onLoginClick() {
        val state = _uiState.value
        if (!state.canLogin || state.isLoginSuccessful) return

        val isLoginSuccessful = AuthRepository.login(
            email = state.emailState.text.toString(),
            password = state.passwordState.text.toString()
        )

        _uiState.update {
            it.copy(
                isLoginSuccessful = isLoginSuccessful,
                loginError = if (isLoginSuccessful) null else "이메일 또는 비밀번호가 올바르지 않아요."
            )
        }
    }

    fun onLoginSuccessHandled() {
        _uiState.update {
            it.copy(isLoginSuccessful = false)
        }
    }

    fun onLoginErrorShown() {
        _uiState.update {
            it.copy(loginError = null)
        }
    }
}
