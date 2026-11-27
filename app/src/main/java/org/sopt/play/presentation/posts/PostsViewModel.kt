package org.sopt.play.presentation.posts

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.sopt.play.data.AuthRepository
import org.sopt.play.data.posts.CreatePostRequest
import org.sopt.play.data.posts.PostDto
import org.sopt.play.data.posts.RetrofitClient

data class PostsUiState(val posts: List<PostDto> = emptyList())

class PostsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PostsUiState())
    val uiState = _uiState.asStateFlow()
    private val _errors = Channel<String>(Channel.BUFFERED)
    val errors = _errors.receiveAsFlow()

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

    fun createPost(title: String, content: String) {
        viewModelScope.launch {
            val author = AuthRepository.registeredName
            if (author == null) {
                _errors.send("회원가입한 이름을 확인할 수 없어요.")
                return@launch
            }
            try {
                RetrofitClient.postService.createPost(
                    AuthRepository.getAuthorizationCode(),
                    CreatePostRequest(title = title, content = content, author = author)
                )
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _errors.send("게시글을 작성하지 못했어요.")
                return@launch
            }
            loadPosts()
        }
    }
}
