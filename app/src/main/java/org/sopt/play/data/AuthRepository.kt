package org.sopt.play.data

object AuthRepository {
    private var registeredEmail: String? = null
    private var registeredPassword: String? = null

    fun register(
        email: String,
        password: String
    ) {
        registeredEmail = email
        registeredPassword = password
    }

    fun login(
        email: String,
        password: String
    ): Boolean = email == registeredEmail && password == registeredPassword
}
