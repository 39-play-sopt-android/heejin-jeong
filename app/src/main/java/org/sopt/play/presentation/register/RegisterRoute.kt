package org.sopt.play.presentation.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.play.presentation.RegisterScreen

@Composable
fun RegisterRoute(
    onRegisterSuccess: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isRegistered) {
        if (uiState.isRegistered) {
            onRegisterSuccess()
            viewModel.onRegisterSuccessHandled()
        }
    }

    RegisterScreen(
        uiState = uiState,
        onRegisterClick = viewModel::onRegisterClick,
        modifier = modifier
    )
}
