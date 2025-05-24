package org.abanapps.regal_time.store.data.domain

import dev.gitlive.firebase.auth.FirebaseUser

interface CustomerRepository {

    suspend fun createCustomer(
        user: FirebaseUser?,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

}