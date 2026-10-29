package org.sopt.play.presentation.util

import android.util.Patterns

fun isValidEmail(email: String): Boolean =
    email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
