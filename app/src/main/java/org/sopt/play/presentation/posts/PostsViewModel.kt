package org.sopt.play.presentation.posts

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.play.data.posts.PostDto
import org.sopt.play.data.posts.RetrofitClient

data class PostsUiState(val posts: List<PostDto> = emptyList())

class PostsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PostsUiState())
    val uiState = _uiState.asStateFlow()
    init {
        viewModelScope.launch {
            loadPosts()
        }
    }

    private suspend fun loadPosts() {
        try {
            val response = RetrofitClient.postService.getPosts()
            _uiState.value = PostsUiState(posts = response.data.posts)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("PostsViewModel", "게시글 목록 조회 실패", e)
        }
    }
}
