package org.sopt.play.data.posts

import kotlinx.serialization.Serializable

@Serializable
data class AuthorizationCodeRequest(val part: String)

@Serializable
data class AuthorizationCodeResponse(val data: AuthorizationCodeData)

@Serializable
data class AuthorizationCodeData(val authorizationCode: String)
