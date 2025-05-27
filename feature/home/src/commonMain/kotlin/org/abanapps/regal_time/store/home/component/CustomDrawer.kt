package org.abanapps.regal_time.store.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.abanapps.regal_time.store.home.domain.DrawerItem
import org.abanapps.regal_time.store.shared.BebasNeueFont
import org.abanapps.regal_time.store.shared.FontSize
import org.abanapps.regal_time.store.shared.SurfaceLighter
import org.abanapps.regal_time.store.shared.TextPrimary
import org.abanapps.regal_time.store.shared.TextSecondary

@Composable
fun CustomDrawer(
    onProfileClick: () -> Unit,
    onContactUsClick: () -> Unit,
    onBlogClick: () -> Unit,
    onLocationClick: () -> Unit,
    onSignOutClick: () -> Unit,
    onAdminClick: () -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxHeight().fillMaxWidth(0.6f)
            .padding(horizontal = 12.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            "REGALTIME STORE",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = TextSecondary,
            fontFamily = BebasNeueFont(),
            fontSize = FontSize.LARGE
        )
        Text(
            "Luxury in hands",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = TextPrimary,
            fontSize = FontSize.REGULAR
        )
        Spacer(modifier = Modifier.height(50.dp))
        DrawerItem.entries.take(5).forEach { item ->
            DrawerItemCard(
                modifier = Modifier.fillMaxWidth(),
                drawerItem = item,
                onClick = {
                    when(item){
                        DrawerItem.Profile -> onProfileClick()
                        DrawerItem.Blog -> onBlogClick()
                        DrawerItem.Location -> onLocationClick()
                        DrawerItem.Contact -> onContactUsClick()
                        DrawerItem.SignOut -> onSignOutClick()
                       else -> {}
                    }
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.weight(1f))
        DrawerItemCard(
            modifier = Modifier.fillMaxWidth(),
            drawerItem = DrawerItem.Admin,
            onClick = {
                onAdminClick()
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
    }

}