package org.abanapps.regal_time.store.data.domain

import dev.gitlive.firebase.auth.FirebaseUser
import org.abanapps.regal_time.store.shared.utlil.RequestState

interface CustomerRepository {

    fun getCurrentUserId(): String?

    suspend fun createCustomer(
        user: FirebaseUser?,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
    )

    suspend fun signOut(): RequestState<Unit>

}