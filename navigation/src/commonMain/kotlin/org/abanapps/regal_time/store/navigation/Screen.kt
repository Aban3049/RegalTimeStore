package org.abanapps.regal_time.store.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {

    @Serializable
    data object Auth: Screen()

}