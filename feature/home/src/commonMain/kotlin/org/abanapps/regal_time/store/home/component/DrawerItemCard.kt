package org.abanapps.regal_time.store.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.abanapps.regal_time.store.home.domain.DrawerItem
import org.abanapps.regal_time.store.shared.FontSize
import org.abanapps.regal_time.store.shared.IconPrimary
import org.abanapps.regal_time.store.shared.TextPrimary
import org.jetbrains.compose.resources.painterResource

@Composable
fun DrawerItemCard(
    modifier: Modifier = Modifier,
    drawerItem: DrawerItem,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth().clip(RoundedCornerShape(99.dp)).clickable {
            onClick()
        }.padding(vertical = 12.dp, horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(drawerItem.icon),
            contentDescription = "Drawer_item_icon", tint = IconPrimary
        )
        Text(
            drawerItem.title, color = TextPrimary,
            fontSize = FontSize.EXTRA_REGULAR
        )
    }
}