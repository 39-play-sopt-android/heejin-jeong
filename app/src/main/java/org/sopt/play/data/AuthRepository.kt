package org.sopt.play.data

object AuthRepository {
    var registeredName: String? = null
        private set
    private var registeredEmail: String? = null
    private var registeredPassword: String? = null

    fun register(
        name: String,
        email: String,
        password: String
    ) {
        registeredName = name
        registeredEmail = email
        registeredPassword = password
    }

    fun login(
        email: String,
        password: String
    ): Boolean = email == registeredEmail && password == registeredPassword
}
