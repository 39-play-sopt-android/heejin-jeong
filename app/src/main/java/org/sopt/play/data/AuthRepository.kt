package org.sopt.play.data

import org.sopt.play.data.posts.AuthorizationCodeRequest
import org.sopt.play.data.posts.RetrofitClient

object AuthRepository {
    var registeredName: String? = null
        private set
    private var authorizationCode: String? = null
    private var registeredEmail: String? = null
    private var registeredPassword: String? = null

    fun register(
        name: String,
        email: String,
        password: String
    ) {
        registeredName = name
        authorizationCode = null
        registeredEmail = email
        registeredPassword = password
    }

    fun login(
        email: String,
        password: String
    ): Boolean = email == registeredEmail && password == registeredPassword

    suspend fun getAuthorizationCode(): String {
        authorizationCode?.let { return it }
        val response = RetrofitClient.postService.issueCode(AuthorizationCodeRequest("ANDROID"))
        return response.data.authorizationCode.also { authorizationCode = it }
    }
}
