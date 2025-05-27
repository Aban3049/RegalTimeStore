package org.abanapps.regal_time.store.home.domain

import org.abanapps.regal_time.store.shared.Resources
import org.abanapps.regal_time.store.shared.navigation.Screen
import org.jetbrains.compose.resources.DrawableResource

enum class BottomBarDestination(
    val icon: DrawableResource,
    val title: String,
    val screen: Screen
){
    ProductOverview(
        icon = Resources.Icon.Home,
        title = "RegalTime Store",
        screen = Screen.ProductsOverview
    ),
    Cart(
        icon = Resources.Icon.ShoppingCart,
        title = "Cart",
        screen = Screen.Cart
    ),
    Categories(
        icon = Resources.Icon.Categories,
        title = "Categories",
        screen = Screen.Categories
    )
}