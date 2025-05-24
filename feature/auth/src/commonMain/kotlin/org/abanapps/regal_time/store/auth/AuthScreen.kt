package org.abanapps.regal_time.store.auth

import ContentWithMessageBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mmk.kmpauth.firebase.apple.AppleButtonUiContainer
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import com.mmk.kmpauth.google.GoogleButtonUiContainer
import org.abanapps.regal_time.store.auth.components.AppleIdButton
import org.abanapps.regal_time.store.auth.components.GoogleButton
import org.abanapps.regal_time.store.shared.Alpha
import org.abanapps.regal_time.store.shared.BebasNeueFont
import org.abanapps.regal_time.store.shared.FontSize
import org.abanapps.regal_time.store.shared.Surface
import org.abanapps.regal_time.store.shared.SurfaceBrand
import org.abanapps.regal_time.store.shared.SurfaceError
import org.abanapps.regal_time.store.shared.TextPrimary
import org.abanapps.regal_time.store.shared.TextSecondary
import org.abanapps.regal_time.store.shared.TextWhite
import org.koin.compose.viewmodel.koinViewModel
import rememberMessageBarState

@Composable
fun AuthScreen() {

    val messageBarState = rememberMessageBarState()
    var loadingState by remember { mutableStateOf(false) }
    val viewModel = koinViewModel<AuthViewModel>()

    Scaffold { padding ->

        ContentWithMessageBar(
            modifier = Modifier.padding(
                top = padding.calculateTopPadding(),
                bottom = padding.calculateBottomPadding()
            ),
            messageBarState = messageBarState,
            errorMaxLines = 2,
            contentBackgroundColor = Surface,
            errorContentColor = TextWhite,
            errorContainerColor = SurfaceError,
            successContainerColor = SurfaceBrand,
            successContentColor = TextPrimary
        ) {

            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(all = 24.dp)
            ) {

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "REGALTIME STORE",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontFamily = BebasNeueFont(),
                        fontSize = FontSize.EXTRA_LARGE,
                        color = TextSecondary
                    )
                    Text(
                        "Sign in to continue",
                        modifier = Modifier.fillMaxWidth()
                            .alpha(Alpha.HALF),
                        textAlign = TextAlign.Center,
                        fontSize = FontSize.EXTRA_REGULAR,
                        color = TextPrimary,

                        )
                }


                GoogleButtonUiContainerFirebase(
                    linkAccount = false,
                    onResult = { result ->
                        result.onSuccess { user ->
                            viewModel.createCustomer(
                                user = user,
                                onSuccess = { messageBarState.addSuccess("Authentication Successfully") },
                                onFailure = { error -> messageBarState.addError(error) }
                            )
                            loadingState = false
                        }.onFailure { error ->
                            if (error.message?.contains("A network error") == true) {
                                messageBarState.addError("Internet connection unavailable")
                            } else if (error.message?.contains("Idtoken is null") == true) {
                                messageBarState.addError("Sign in cancelled.")
                            } else {
                                messageBarState.addError(error.message ?: "Unknown")
                            }
                            loadingState = false
                        }
                    }
                ) {
                    GoogleButton(
                        loading = loadingState
                    ) {
                        loadingState = true
                        this@GoogleButtonUiContainerFirebase.onClick()
                    }
                }
                if (getPlatform == "IOS") {
                    Spacer(modifier = Modifier.height(16.dp))

                    AppleButtonUiContainer(onResult = { result ->
                        result.onSuccess {
                            messageBarState.addSuccess("Authentication Successfully")
                            loadingState = false
                        }.onFailure { error ->
                            if (error.message?.contains("A network error") == true) {
                                messageBarState.addError("Internet connection unavailable")
                            } else if (error.message?.contains("Idtoken is null") == true) {
                                messageBarState.addError("Sign in cancelled.")
                            } else {
                                messageBarState.addError(error.message ?: "Unknown")
                            }
                            loadingState = false
                        }
                    }, linkAccount = false) {
                        AppleIdButton {
                            this@AppleButtonUiContainer.onClick()
                        }
                    }

                }

            }

        }

    }
}
