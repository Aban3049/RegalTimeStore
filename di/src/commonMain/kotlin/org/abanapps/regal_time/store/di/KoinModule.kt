package org.abanapps.regal_time.store.di

import org.abanapps.regal_time.store.auth.AuthViewModel
import org.abanapps.regal_time.store.home.HomeGraphViewModel
import org.abanapps.regal_time.store.data.domain.CustomerRepository
import org.abanapps.regal_time.store.data.domain.CustomerRepositoryImpl
import org.abanapps.regal_time.store.profile.ProfileViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedModule = module {
    single<CustomerRepository> { CustomerRepositoryImpl() }
    viewModelOf(::AuthViewModel)
    viewModelOf(::HomeGraphViewModel)
    viewModelOf(::ProfileViewModel)
}

fun initializeKoin(
    config: (KoinApplication.() -> Unit)? =null
){

    startKoin {
        config?.invoke(this)
        modules(sharedModule)
    }

}