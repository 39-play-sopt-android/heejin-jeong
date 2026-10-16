package org.sopt.play.presentation

import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    private var registeredEmail: String? = null
    private var registeredPassword: String? = null

    fun register(
        email: String,
        password: String
    ) {
        registeredEmail = email
        registeredPassword = password
    }

    fun canLogin(
        email: String,
        password: String
    ): Boolean = email == registeredEmail && password == registeredPassword
}
