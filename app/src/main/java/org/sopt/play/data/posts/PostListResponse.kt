package org.sopt.play.data.posts

import kotlinx.serialization.Serializable

@Serializable
data class PostListResponse(val data: PostListData)

@Serializable
data class PostListData(val posts: List<PostDto>)

@Serializable
data class PostDto(
    val id: Long,
    val author: String,
    val createdAt: String,
    val title: String,
    val content: String
)
