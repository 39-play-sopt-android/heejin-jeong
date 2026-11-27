package org.sopt.play.data.posts

import kotlinx.serialization.Serializable

@Serializable
data class CreatePostRequest(val title: String, val content: String, val author: String)
