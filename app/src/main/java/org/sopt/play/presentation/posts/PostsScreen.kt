package org.sopt.play.presentation.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.play.R
import org.sopt.play.data.posts.PostDto
import org.sopt.play.ui.theme.PlayColors
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.PlayType

@Composable
fun PostsRoute(viewModel: PostsViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    PostsScreen(
        posts = uiState.posts,
        onCreate = { _, _ -> }
    )
}

@Composable
private fun PostsScreen(
    posts: List<PostDto>,
    onCreate: (String, String) -> Unit
) {
    var showWriter by rememberSaveable { mutableStateOf(false) }

    if (showWriter) {
        CreatePostSheet(
            onDismiss = { showWriter = false },
            onSubmit = { title, content ->
                onCreate(title, content)
                showWriter = false
            }
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PlayColors.White)
            .safeDrawingPadding()
            .padding(horizontal = 16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(2.dp, PlayColors.Gray2, RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.posts_search_hint),
                    modifier = Modifier.weight(1f),
                    style = PlayType.m18,
                    color = PlayColors.Gray2
                )
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                    tint = PlayColors.Gray3,
                    modifier = Modifier.size(28.dp)
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(top = 12.dp, bottom = 96.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(posts, key = { it.id }) { post ->
                    PostCard(post)
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 20.dp)
                .size(60.dp)
                .shadow(4.dp, CircleShape)
                .background(PlayColors.Red, CircleShape)
                .clickable(role = Role.Button) { showWriter = true },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_post_plus),
                contentDescription = "게시글 작성",
                tint = PlayColors.White
            )
        }
    }
}

@Composable
private fun PostCard(post: PostDto) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(PlayColors.Gray1, RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val metaStyle = PlayType.m14.copy(fontSize = 12.sp)
            val displayDate = post.createdAt.substringBefore('T').replace('-', '.')
            Text(post.author, style = metaStyle, color = PlayColors.Gray3)
            Spacer(
                modifier = Modifier
                    .width(1.dp)
                    .height(12.dp)
                    .background(PlayColors.Gray3)
            )
            Text(text = displayDate, style = metaStyle, color = PlayColors.Gray3)
        }
        Spacer(Modifier.height(8.dp))
        Text(post.title, style = PlayType.m14.copy(fontSize = 16.sp), color = PlayColors.Gray5)
        Spacer(Modifier.height(4.dp))
        Text(post.content, style = PlayType.m14, color = PlayColors.Gray5)
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun PostsScreenPreview() {
    PlaySoptTheme {
        PostsScreen(emptyList(), onCreate = { _, _ -> })
    }
}
