package org.abanapps.regal_time.store

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun NativeButton(onClick: () -> Unit, modifier: Modifier) {
    Button(
        onClick = {
            onClick()
        },
        modifier = modifier
    ) {
        Text("Native Button Android")
    }
}