package org.abanapps.regal_time.store.data.domain

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import org.abanapps.regal_time.store.shared.domain.Customer
import org.abanapps.regal_time.store.shared.utlil.RequestState

class CustomerRepositoryImpl : CustomerRepository {


    override fun getCurrentUserId(): String? {
        return Firebase.auth.currentUser?.uid
    }

    override suspend fun createCustomer(
        user: FirebaseUser?,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
    ) {
        try {
            if (user != null) {

                val customerCollection = Firebase.firestore.collection(collectionPath = "customer")
                val customer = Customer(
                    id = user.uid,
                    firstName = user.displayName?.split(" ")?.firstOrNull() ?: "Unknown",
                    lastName = user.displayName?.split(" ")?.lastOrNull() ?: "Unknown",
                    email = user.email ?: "Unknown",
                    profileImageUrl = user.photoURL ?: ""
                )

                val customerExist = customerCollection.document(user.uid).get().exists
                if (customerExist){
                    onSuccess()
                }else{
                    customerCollection.document(user.uid).set(customer)
                    onSuccess()
                }

            }

            else {
                onFailure("User is null")
            }

        }

        catch (e: Exception) {
            onFailure("Error while creating a Customer: ${e.message}")
        }
    }

    override suspend fun signOut(): RequestState<Unit> {
        return try {
            Firebase.auth.signOut()
            RequestState.Success(data = Unit)
        }catch (e: Exception){
            RequestState.Error("Error while signing out: ${e.message}")
        }
    }

}