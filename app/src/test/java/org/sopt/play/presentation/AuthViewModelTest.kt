package org.sopt.play.presentation

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AuthViewModelTest {
    @Test
    fun loginFailsBeforeRegistration() {
        val viewModel = AuthViewModel()

        assertFalse(
            viewModel.canLogin(
                email = "",
                password = ""
            )
        )
        assertFalse(
            viewModel.canLogin(
                email = "test@example.com",
                password = "123456"
            )
        )
    }

    @Test
    fun loginRequiresBothEmailAndPasswordToMatch() {
        val viewModel = AuthViewModel()
        viewModel.register(
            email = "test@example.com",
            password = "123456"
        )

        assertTrue(
            viewModel.canLogin(
                email = "test@example.com",
                password = "123456"
            )
        )
        assertFalse(
            viewModel.canLogin(
                email = "other@example.com",
                password = "123456"
            )
        )
        assertFalse(
            viewModel.canLogin(
                email = "test@example.com",
                password = "654321"
            )
        )
    }

    @Test
    fun registeringAgainReplacesPreviousAccount() {
        val viewModel = AuthViewModel()
        viewModel.register(
            email = "old@example.com",
            password = "123456"
        )
        viewModel.register(
            email = "new@example.com",
            password = "654321"
        )

        assertFalse(
            viewModel.canLogin(
                email = "old@example.com",
                password = "123456"
            )
        )
        assertTrue(
            viewModel.canLogin(
                email = "new@example.com",
                password = "654321"
            )
        )
    }
}
