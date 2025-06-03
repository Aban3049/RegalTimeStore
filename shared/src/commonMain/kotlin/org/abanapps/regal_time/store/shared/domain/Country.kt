package org.abanapps.regal_time.store.shared.domain

import org.abanapps.regal_time.store.shared.Resources
import org.jetbrains.compose.resources.DrawableResource

enum class Country(
    val dialCode: Int,
    val code: String,
    val flag: DrawableResource,
) {

    Pakistan(
        dialCode = 92,
        code = "PK",
        flag = Resources.Flag.Pakistan
    ),

    Serbia(
        dialCode = 381,
        code = "RS",
        flag = Resources.Flag.Serbia
    ),

    Usa(
        dialCode = 1,
        code = "US",
        flag = Resources.Flag.Usa
    )

}