package org.sopt.play.ui.modifier

import androidx.compose.foundation.clickable
import androidx.compose.foundation.selection.selectable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role

fun Modifier.noRippleClickable(
    role: Role,
    onClick: () -> Unit,
    enabled: Boolean = true
): Modifier = clickable(
    interactionSource = null,
    indication = null,
    enabled = enabled,
    role = role,
    onClick = onClick
)

fun Modifier.noRippleSelectable(
    selected: Boolean,
    role: Role,
    onClick: () -> Unit
): Modifier = selectable(
    selected = selected,
    interactionSource = null,
    indication = null,
    role = role,
    onClick = onClick
)
