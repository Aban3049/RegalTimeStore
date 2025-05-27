package org.abanapps.regal_time.store.home.domain

import org.abanapps.regal_time.store.shared.Resources
import org.jetbrains.compose.resources.DrawableResource

enum class DrawerItem(
    val title: String,
    val icon: DrawableResource,
)
{
    Profile(
        title = "Profile",
        icon = Resources.Icon.Person
    ),
    Blog(
        title = "Blog",
        icon = Resources.Icon.Book
    ),
    Location(
        title = "Location",
        icon = Resources.Icon.MapPin
    ),
    Contact(
        title = "Contact us",
        icon = Resources.Icon.Edit
    ),
    SignOut(
        title = "Sing Out",
        icon = Resources.Icon.SignOut
    ),
    Admin(
        title = "Admin Panel",
        icon = Resources.Icon.Unlock
    )

}