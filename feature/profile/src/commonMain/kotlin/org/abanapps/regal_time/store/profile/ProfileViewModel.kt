package org.abanapps.regal_time.store.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.abanapps.regal_time.store.data.domain.CustomerRepository
import org.abanapps.regal_time.store.shared.domain.Country
import org.abanapps.regal_time.store.shared.domain.PhoneNumber
import org.abanapps.regal_time.store.shared.utlil.RequestState

data class ProfileScreenState(
    val firstName: String = "",
    val lastName: String = "",
    val profileImagwUrl: String = "",
    val email: String = "",
    val city: String? = null,
    val postalCode: Int? = null,
    val address: String? = null,
    val country: Country = Country.Pakistan,
    val phoneNumber: PhoneNumber? = null,
)

class ProfileViewModel(
    private val customerRepository: CustomerRepository,
) : ViewModel() {

    private val customer = customerRepository.readCustomerFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = RequestState.Loading
    )

    var screenState: ProfileScreenState by mutableStateOf(ProfileScreenState())
        private set

    var screenReady: RequestState<Unit> by mutableStateOf(RequestState.Loading)

    init {
        viewModelScope.launch {
            customer.collectLatest { data ->
                if (data.isSuccess()) {
                    val fetchedCustomer = data.getSuccessData()
                    screenState = ProfileScreenState(
                        firstName = fetchedCustomer.firstName,
                        lastName = fetchedCustomer.lastName,
                        email = fetchedCustomer.email,
                        city = fetchedCustomer.city,
                        postalCode = fetchedCustomer.postalCode,
                        address = fetchedCustomer.address,
                        phoneNumber = fetchedCustomer.phoneNumber,
                        profileImagwUrl = fetchedCustomer.profileImageUrl,
                        country = Country.entries.firstOrNull { it.dialCode == fetchedCustomer.phoneNumber?.dialCode }
                            ?: Country.Pakistan
                    )
                    screenReady = RequestState.Success(Unit)
                } else if (data.isError()) {
                    screenReady = RequestState.Error(message = data.getErrorMessage())
                }
            }
        }
    }

    fun updateFirstName(value: String) {
        screenState = screenState.copy(firstName = value)

    }

    fun updateLastName(value: String) {
        screenState = screenState.copy(lastName = value)
    }

    fun updateCity(value: String) {
        screenState = screenState.copy(city = value)

    }

    fun updatePostalCode(value: Int?) {
        screenState = screenState.copy(postalCode = value)
    }

    fun updateAddress(value: String?) {
        screenState = screenState.copy(address = value)
    }

    fun updateCountry(value: Country) {
        screenState = screenState.copy(country = value)

    }

    fun updatePhoneNumber(value: String) {
        screenState = screenState.copy(
            phoneNumber = PhoneNumber(
                dialCode = screenState.country.dialCode,
                number = value
            )
        )

    }

}