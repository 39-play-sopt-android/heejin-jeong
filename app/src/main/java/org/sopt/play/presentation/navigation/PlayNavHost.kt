package org.sopt.play.presentation.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.play.presentation.AuthViewModel
import org.sopt.play.presentation.HomeScreen
import org.sopt.play.presentation.LoginScreen
import org.sopt.play.presentation.RegisterScreen

@Composable
fun PlayNavHost(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Route.Login,
        modifier = modifier
    ) {
        composable<Route.Login> { backStackEntry ->
            LoginScreen(
                onRegisterClick = {
                    if (navController.currentBackStackEntry != backStackEntry) {
                        return@LoginScreen
                    }

                    navController.navigate(Route.Register) {
                        launchSingleTop = true
                    }
                },
                onLoginClick = { email, password ->
                    if (navController.currentBackStackEntry != backStackEntry) {
                        return@LoginScreen
                    }

                    val canLogin = authViewModel.canLogin(
                        email = email,
                        password = password
                    )

                    if (canLogin) {
                        navController.navigate(Route.Home) {
                            popUpTo<Route.Login> {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "이메일 또는 비밀번호가 올바르지 않아요.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
        }

        composable<Route.Register> { backStackEntry ->
            RegisterScreen(
                onRegisterClick = { email, password ->
                    if (navController.currentBackStackEntry != backStackEntry) {
                        return@RegisterScreen
                    }

                    authViewModel.register(
                        email = email,
                        password = password
                    )
                    navController.popBackStack()
                }
            )
        }

        composable<Route.Home> {
            HomeScreen()
        }
    }
}
