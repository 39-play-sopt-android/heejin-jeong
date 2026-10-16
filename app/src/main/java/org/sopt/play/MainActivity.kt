package org.sopt.play

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import org.sopt.play.presentation.AuthViewModel
import org.sopt.play.presentation.navigation.PlayNavHost
import org.sopt.play.ui.theme.PlaySoptTheme

class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PlaySoptTheme {
                val navController = rememberNavController()

                PlayNavHost(
                    navController = navController,
                    authViewModel = authViewModel
                )
            }
        }
    }
}
