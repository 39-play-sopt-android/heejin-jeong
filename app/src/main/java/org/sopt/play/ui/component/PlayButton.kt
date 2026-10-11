package org.sopt.play.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.theme.PlayColors
import org.sopt.play.ui.theme.PlayType

@Composable
fun PlayButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.fillMaxWidth().height(50.dp),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = PlayColors.Black,
            contentColor = PlayColors.White,
            disabledContainerColor = PlayColors.Gray1,
            disabledContentColor = PlayColors.Gray3
        )
    ) {
        Text(text, style = PlayType.sb14)
    }
}
