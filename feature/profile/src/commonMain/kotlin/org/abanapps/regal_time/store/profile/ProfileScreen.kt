package org.abanapps.regal_time.store.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.abanapps.regal_time.store.shared.BebasNeueFont
import org.abanapps.regal_time.store.shared.FontSize
import org.abanapps.regal_time.store.shared.IconPrimary
import org.abanapps.regal_time.store.shared.Resources
import org.abanapps.regal_time.store.shared.Surface
import org.abanapps.regal_time.store.shared.TextPrimary
import org.abanapps.regal_time.store.shared.component.ErrorCard
import org.abanapps.regal_time.store.shared.component.LoadingCard
import org.abanapps.regal_time.store.shared.component.PrimaryButton
import org.abanapps.regal_time.store.shared.component.ProfileForm
import org.abanapps.regal_time.store.shared.utlil.DisplayResult
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navigateBack: () -> Unit,
) {


    val viewModel = koinViewModel<ProfileViewModel>()
    val screenState = viewModel.screenState
    val screenReady = viewModel.screenReady

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My Profile",
                        fontFamily = BebasNeueFont(),
                        fontSize = FontSize.LARGE,
                        color = TextPrimary
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = navigateBack
                    ) {
                        Icon(
                            painter = painterResource(Resources.Icon.BackArrow),
                            contentDescription = "Back Arrow icon",
                            tint = IconPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Surface,
                    scrolledContainerColor = Surface,
                    navigationIconContentColor = IconPrimary,
                    titleContentColor = TextPrimary,
                    actionIconContentColor = IconPrimary
                )
            )
        },
        containerColor = Surface
    ) { innerPadding ->

        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 24.dp)
                .padding(top = 12.dp, bottom = 24.dp)
        ) {

            screenReady.DisplayResult(
                onLoading = {
                    LoadingCard(modifier = Modifier.fillMaxSize())
                },
                onSuccess = { state ->
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        ProfileForm(
                            modifier = Modifier.weight(1f),
                            firstName = screenState.firstName,
                            onFirstNameChange = viewModel::updateFirstName,
                            lastName = screenState.lastName,
                            onLastNameChange = viewModel::updateLastName,
                            email = screenState.email,
                            city = screenState.city,
                            onCityChange = viewModel::updateCity,
                            postalCode = screenState.postalCode,
                            onPostalCodeChange = viewModel::updatePostalCode,
                            address = screenState.address,
                            onAddressChange = viewModel::updateAddress,
                            phoneNumber = screenState.phoneNumber?.number,
                            onPhoneNumberChange = viewModel::updatePhoneNumber,
                            country = screenState.country,
                            onCountryChange = viewModel::updateCountry
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        PrimaryButton(
                            text = "Update",
                            icon = Resources.Icon.Checkmark
                        ) { }
                    }

                },
                onError = { message ->
                    ErrorCard(
                        modifier = Modifier,
                        message = message,
                        fontSize = FontSize.REGULAR
                    )
                },

                )

        }


    }


}