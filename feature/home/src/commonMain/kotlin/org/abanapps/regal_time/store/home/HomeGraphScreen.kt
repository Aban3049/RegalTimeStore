package org.abanapps.regal_time.store.home

import ContentWithMessageBar
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.abanapps.regal_time.store.home.component.BottomBar
import org.abanapps.regal_time.store.home.component.CustomDrawer
import org.abanapps.regal_time.store.home.domain.BottomBarDestination
import org.abanapps.regal_time.store.home.domain.CustomDrawerState
import org.abanapps.regal_time.store.home.domain.isOpened
import org.abanapps.regal_time.store.home.domain.opposite
import org.abanapps.regal_time.store.shared.Alpha
import org.abanapps.regal_time.store.shared.BebasNeueFont
import org.abanapps.regal_time.store.shared.FontSize
import org.abanapps.regal_time.store.shared.IconPrimary
import org.abanapps.regal_time.store.shared.Resources
import org.abanapps.regal_time.store.shared.Surface
import org.abanapps.regal_time.store.shared.SurfaceLighter
import org.abanapps.regal_time.store.shared.TextPrimary
import org.abanapps.regal_time.store.shared.navigation.Screen
import org.abanapps.regal_time.store.shared.utlil.getScreenWidth
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import rememberMessageBarState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeGraphScreen(
    navigateToAuth:() -> Unit
) {

    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState()
    val viewModel = koinViewModel<HomeGraphViewModel>()

    val selectedDestination by remember {
        derivedStateOf {
            val route = currentRoute.value?.destination?.route.toString()
            when {
                route.contains(BottomBarDestination.ProductOverview.screen.toString()) -> BottomBarDestination.ProductOverview
                route.contains(BottomBarDestination.Cart.screen.toString()) -> BottomBarDestination.Cart
                route.contains(BottomBarDestination.Categories.screen.toString()) -> BottomBarDestination.Categories
                else -> BottomBarDestination.ProductOverview
            }
        }
    }

    val screenWidth = remember {
        getScreenWidth()
    }
    var drawerState by remember {
        mutableStateOf(CustomDrawerState.Closed)
    }

    val offsetValue by remember { derivedStateOf { (screenWidth / 1.5).dp } }

    val animateOffset by animateDpAsState(
        targetValue = if (drawerState.isOpened()) offsetValue else 0.dp
    )

    val animateBackground by animateColorAsState(
        targetValue = if (drawerState.isOpened()) SurfaceLighter else Surface
    )

    val animateScale by animateFloatAsState(
        targetValue = if (drawerState.isOpened()) 0.9f else 1f
    )

    val animateRadius by animateDpAsState(
        targetValue = if (drawerState.isOpened()) 20.dp else 0.dp
    )

    val messageBarState = rememberMessageBarState()

    Box(modifier = Modifier.fillMaxSize().background(animateBackground).systemBarsPadding()) {
        CustomDrawer(
            onBlogClick = {},
            onContactUsClick = {},
            onLocationClick = {},
            onProfileClick = {},
            onSignOutClick = {
                viewModel.signOut(
                    onSuccess = {
                        navigateToAuth()
                    },
                    onError = {message ->
                        messageBarState.addError(message)
                    }
                )
            },
            onAdminClick = {}
        )
        Box(
            modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(animateRadius))
                .offset(x = animateOffset).scale(scale = animateScale)
                .shadow(
                    20.dp,
                    RoundedCornerShape(animateRadius),
                    ambientColor = Color.Black.copy(alpha = Alpha.DISABLED),
                    spotColor = Color.Black.copy(
                        Alpha.DISABLED
                    )
                )
        ) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            AnimatedContent(targetState = selectedDestination) { destination ->
                                Text(
                                    text = destination.title,
                                    fontFamily = BebasNeueFont(),
                                    fontSize = FontSize.LARGE,
                                    color = TextPrimary
                                )
                            }
                        },
                        navigationIcon = {
                            AnimatedContent(
                                targetState = drawerState
                            ) { drawer ->
                                if (drawer.isOpened()) {
                                    IconButton(
                                        onClick = {
                                            drawerState = drawerState.opposite()
                                        }
                                    ) {
                                        Icon(
                                            painter = painterResource(Resources.Icon.Close),
                                            contentDescription = "ic_close",
                                            tint = IconPrimary
                                        )
                                    }
                                } else {
                                    IconButton(onClick = {
                                        drawerState = drawerState.opposite()
                                    })
                                    {
                                        Icon(
                                            painter = painterResource(Resources.Icon.Menu),
                                            contentDescription = "menu_icon",
                                            tint = IconPrimary
                                        )
                                    }
                                }

                            }

                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Surface,
                            scrolledContainerColor = Surface,
                            navigationIconContentColor = IconPrimary,
                            titleContentColor = TextPrimary,
                            actionIconContentColor = IconPrimary
                        )
                    )
                },
                containerColor = Surface,
            ) { innerPadding ->
                ContentWithMessageBar(
                    messageBarState = messageBarState,
                    modifier = Modifier.fillMaxSize().padding(innerPadding),
                    errorMaxLines = 2,
                    contentBackgroundColor = Surface
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.ProductsOverview,
                            modifier = Modifier.weight(1f)
                        ) {
                            composable<Screen.ProductsOverview> { Text("Product Overview") }
                            composable<Screen.Cart> { Text("Cart") }
                            composable<Screen.Categories> { Text("Category") }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Box(modifier = Modifier.padding(all = 12.dp)) {
                            BottomBar(selected = selectedDestination, onSelected = { destination ->
                                navController.navigate(destination.screen) {
                                    launchSingleTop = true
                                    popUpTo(Screen.ProductsOverview) {
                                        saveState = true
                                        inclusive = false
                                    }
                                    restoreState = true
                                }
                            })
                        }

                    }
                }
            }
        }

    }

}