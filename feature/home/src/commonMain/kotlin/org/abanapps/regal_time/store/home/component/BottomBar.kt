package org.abanapps.regal_time.store.home.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.abanapps.regal_time.store.home.domain.BottomBarDestination
import org.abanapps.regal_time.store.shared.IconPrimary
import org.abanapps.regal_time.store.shared.IconSecondary
import org.abanapps.regal_time.store.shared.SurfaceLighter
import androidx.compose.runtime.getValue
import org.jetbrains.compose.resources.painterResource

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    selected: BottomBarDestination,
    onSelected: (BottomBarDestination) -> Unit,
) {

    Row(
        modifier = modifier.fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceLighter).padding(
                vertical = 12.dp,
                horizontal = 12.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        BottomBarDestination.entries.forEach { destination ->

            val animateTint by animateColorAsState(
                targetValue = if (selected == destination) IconSecondary else IconPrimary,
            )

            IconButton(
                onClick = {
                    onSelected(destination)
                }
            ) {
                Icon(
                    painter = painterResource(destination.icon),
                    tint = animateTint,
                    contentDescription = destination.title
                )
            }

        }

    }
}