package org.abanapps.regal_time.store.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.abanapps.regal_time.store.data.domain.CustomerRepository

class AuthViewModel (
    private val customerRepository: CustomerRepository
): ViewModel(){
    fun createCustomer(
        user: FirebaseUser?,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) = viewModelScope.launch(Dispatchers.IO) {
        customerRepository.createCustomer(
            user = user,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}