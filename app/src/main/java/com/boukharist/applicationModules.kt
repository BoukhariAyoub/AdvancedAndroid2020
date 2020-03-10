package com.boukharist

import com.boukharist.presentation.modernandroiddevelopment.screens.registration.UserRegistrationActivity
import org.koin.core.qualifier.named
import org.koin.dsl.module

val scopes = module {
    single(named<UserRegistrationActivity>()) { getKoin().getOrCreateScope("registration", named<UserRegistrationActivity>()) }
}

val applicationModules = registrationFormModule + scopes
