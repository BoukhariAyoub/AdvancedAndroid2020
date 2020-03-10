package com.boukharist

import com.boukharist.data.memory.datasource.InMemoryRegistrationFormDataSource
import com.boukharist.data.memory.datasource.InMemoryRegistrationFormDataSourceImpl
import com.boukharist.data.repositories.InMemoryRegistrationFormRepositoryImpl
import com.boukharist.domain.repository.InMemoryRegistrationFormRepository
import com.boukharist.domain.usecase.UserRegistrationFormUseCase
import com.boukharist.domain.usecase.UserRegistrationFormUseCaseImpl
import com.boukharist.presentation.modernandroiddevelopment.screens.registration.RegistrationRouter
import com.boukharist.presentation.modernandroiddevelopment.screens.registration.RegistrationRouterImpl
import com.boukharist.presentation.modernandroiddevelopment.screens.registration.UserRegistrationActivity
import com.boukharist.presentation.modernandroiddevelopment.screens.registration.UserRegistrationViewModel
import com.boukharist.presentation.modernandroiddevelopment.screens.registration.basicinfo.DateProvider
import com.boukharist.presentation.modernandroiddevelopment.screens.registration.basicinfo.DateProviderImpl
import com.boukharist.presentation.modernandroiddevelopment.screens.registration.basicinfo.UserRegistrationBasicInfoViewModel
import com.boukharist.presentation.modernandroiddevelopment.screens.registration.secondaryInfo.UserRegistrationSecondaryViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val registrationFormModule = module {
    scope(named<UserRegistrationActivity>()) {
        //viewModel
        viewModel { UserRegistrationBasicInfoViewModel(get(), get()) }
        viewModel { UserRegistrationSecondaryViewModel(get()) }
        viewModel { UserRegistrationViewModel() }


        //useCase
        scoped<UserRegistrationFormUseCase> { UserRegistrationFormUseCaseImpl(get()) }


        //repos
        scoped<InMemoryRegistrationFormRepository> { InMemoryRegistrationFormRepositoryImpl(get()) }

        //dataSource
        scoped<InMemoryRegistrationFormDataSource> { InMemoryRegistrationFormDataSourceImpl() }

        //utils
        scoped<RegistrationRouter> { RegistrationRouterImpl(androidContext()) }
        scoped<DateProvider> { DateProviderImpl() }
    }
}