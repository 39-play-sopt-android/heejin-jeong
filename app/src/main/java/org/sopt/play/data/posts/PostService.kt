package org.sopt.play.data.posts

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface PostService {
    @POST("api/v1/members/authorization-code")
    suspend fun issueCode(@Body request: AuthorizationCodeRequest): AuthorizationCodeResponse

    @POST("api/v1/posts")
    suspend fun createPost(@Header("Authorization") code: String, @Body request: CreatePostRequest)

    @GET("api/v1/posts")
    suspend fun getPosts(): PostListResponse

    @DELETE("api/v1/posts/{postId}")
    suspend fun deletePost(@Path("postId") postId: Long, @Header("Authorization") code: String)
}
