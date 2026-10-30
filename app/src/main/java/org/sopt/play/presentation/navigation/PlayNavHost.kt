package org.sopt.play.presentation.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.play.presentation.AuthViewModel
import org.sopt.play.presentation.HomeScreen
import org.sopt.play.presentation.LoginScreen
import org.sopt.play.presentation.RegisterScreen
import org.sopt.play.presentation.home.HomeViewModel
import org.sopt.play.presentation.login.LoginViewModel
import org.sopt.play.presentation.register.RegisterViewModel

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
            val loginViewModel: LoginViewModel = viewModel()

            LoginScreen(
                emailState = loginViewModel.emailState,
                passwordState = loginViewModel.passwordState,
                emailError = loginViewModel.emailError,
                passwordError = loginViewModel.passwordError,
                canLogin = loginViewModel.canLogin,
                onRegisterClick = {
                    if (navController.currentBackStackEntry != backStackEntry) {
                        return@LoginScreen
                    }

                    navController.navigate(Route.Register) {
                        launchSingleTop = true
                    }
                },
                onLoginClick = {
                    if (
                        navController.currentBackStackEntry != backStackEntry ||
                        !loginViewModel.canLogin
                    ) {
                        return@LoginScreen
                    }

                    val canLogin = authViewModel.canLogin(
                        email = loginViewModel.emailState.text.toString(),
                        password = loginViewModel.passwordState.text.toString()
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
            val registerViewModel: RegisterViewModel = viewModel()

            RegisterScreen(
                nameState = registerViewModel.nameState,
                emailState = registerViewModel.emailState,
                passwordState = registerViewModel.passwordState,
                passwordConfirmState = registerViewModel.passwordConfirmState,
                emailError = registerViewModel.emailError,
                passwordError = registerViewModel.passwordError,
                confirmError = registerViewModel.confirmError,
                canRegister = registerViewModel.canRegister,
                onRegisterClick = {
                    if (
                        navController.currentBackStackEntry != backStackEntry ||
                        !registerViewModel.canRegister
                    ) {
                        return@RegisterScreen
                    }

                    authViewModel.register(
                        email = registerViewModel.emailState.text.toString(),
                        password = registerViewModel.passwordState.text.toString()
                    )
                    navController.popBackStack()
                }
            )
        }

        composable<Route.Home> {
            val homeViewModel: HomeViewModel = viewModel()

            HomeScreen(
                selectedMenuIndex = homeViewModel.selectedMenuIndex,
                selectedCategoryIndex = homeViewModel.selectedCategoryIndex,
                products = homeViewModel.products,
                onMenuClick = homeViewModel::onMenuClick,
                onCategoryClick = homeViewModel::onCategoryClick
            )
        }
    }
}
