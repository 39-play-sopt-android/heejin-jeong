package org.sopt.play.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.play.presentation.home.HomeRoute
import org.sopt.play.presentation.login.LoginRoute
import org.sopt.play.presentation.register.RegisterRoute

@Composable
fun PlayNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Route.Login,
        modifier = modifier
    ) {
        composable<Route.Login> { backStackEntry ->
            LoginRoute(
                onLoginSuccess = {
                    if (navController.currentBackStackEntry != backStackEntry) {
                        return@LoginRoute
                    }

                    navController.navigate(Route.Home) {
                        popUpTo<Route.Login> {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onRegisterClick = {
                    if (navController.currentBackStackEntry != backStackEntry) {
                        return@LoginRoute
                    }

                    navController.navigate(Route.Register) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<Route.Register> { backStackEntry ->
            RegisterRoute(
                onRegisterSuccess = {
                    if (navController.currentBackStackEntry != backStackEntry) {
                        return@RegisterRoute
                    }

                    navController.popBackStack()
                }
            )
        }

        composable<Route.Home> {
            HomeRoute()
        }
    }
}
