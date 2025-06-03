package org.abanapps.regal_time.store.shared.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import org.abanapps.regal_time.store.shared.FontSize
import org.abanapps.regal_time.store.shared.IconPrimary

@Composable
fun LoadingCard(
    modifier: Modifier = Modifier,

) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = IconPrimary, strokeWidth = 2.dp, modifier = Modifier.size(26.dp))
    }
}