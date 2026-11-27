package org.sopt.play.data.posts

import retrofit2.http.GET

interface PostService {
    @GET("api/v1/posts")
    suspend fun getPosts(): PostListResponse
}
