package org.abanapps.regal_time.store.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.abanapps.regal_time.store.auth.AuthScreen
import org.abanapps.regal_time.store.home.HomeGraphScreen
import org.abanapps.regal_time.store.profile.ProfileScreen
import org.abanapps.regal_time.store.shared.navigation.Screen

@Composable
fun SetupNavGraph(
    startDestination: Screen = Screen.Auth,
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        composable<Screen.Auth> {
            AuthScreen(
                navigateToHome = {
                    navController.navigate(Screen.HomeGraph) {
                        popUpTo<Screen.Auth> { inclusive = true }
                    }
                }
            )
        }

        composable<Screen.HomeGraph> {
            HomeGraphScreen(navigateToAuth = {
                navController.navigate(Screen.Auth) {
                    popUpTo<Screen.HomeGraph> { inclusive = true }
                }
            }, navigateToProfile = {
                navController.navigate(Screen.ProfileScreen)
            })
        }

        composable<Screen.ProfileScreen> {
            ProfileScreen(navigateBack = {
                navController.navigateUp()
            })
        }

    }


}