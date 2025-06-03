package org.abanapps.regal_time.store.shared.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.abanapps.regal_time.store.shared.Alpha
import org.abanapps.regal_time.store.shared.BorderError
import org.abanapps.regal_time.store.shared.BorderIdle
import org.abanapps.regal_time.store.shared.FontSize
import org.abanapps.regal_time.store.shared.IconSecondary
import org.abanapps.regal_time.store.shared.SurfaceDarker
import org.abanapps.regal_time.store.shared.SurfaceLighter
import org.abanapps.regal_time.store.shared.TextPrimary

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    enabled: Boolean = true,
    error: Boolean = false,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
) {

    val borderColor by animateColorAsState(
        targetValue = if (error) BorderError else BorderIdle
    )

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth()
            .border(1.dp, color = borderColor, shape = RoundedCornerShape(6.dp))
            .clip(RoundedCornerShape(6.dp)),
        enabled = enabled,
        placeholder = {
            if (placeholder != null) {
                Text(
                    text = placeholder,
                    fontSize = FontSize.REGULAR
                )
            } else {
                null
            }
        },
        singleLine = singleLine,
        shape = RoundedCornerShape(6.dp),
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = SurfaceLighter,
            unfocusedContainerColor = SurfaceLighter,
            disabledContainerColor = SurfaceDarker,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary,
            disabledTextColor = TextPrimary.copy(Alpha.DISABLED),
            focusedPlaceholderColor = TextPrimary.copy(alpha = Alpha.HALF),
            unfocusedPlaceholderColor = TextPrimary.copy(Alpha.HALF),
            disabledPlaceholderColor = TextPrimary.copy(Alpha.DISABLED),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            selectionColors = TextSelectionColors(
                handleColor = IconSecondary,
                backgroundColor = Color.Transparent
            )

        )
    )


}