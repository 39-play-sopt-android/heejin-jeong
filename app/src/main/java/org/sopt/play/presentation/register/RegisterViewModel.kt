package org.sopt.play.presentation.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.sopt.play.data.AuthRepository

class RegisterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    fun onRegisterClick() {
        val state = _uiState.value
        if (!state.canRegister || state.isRegistered) return

        AuthRepository.register(
            email = state.emailState.text.toString(),
            password = state.passwordState.text.toString()
        )
        _uiState.update {
            it.copy(isRegistered = true)
        }
    }

    fun onRegisterSuccessHandled() {
        _uiState.update {
            it.copy(isRegistered = false)
        }
    }
}
