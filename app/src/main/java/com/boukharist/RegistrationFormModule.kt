package com.boukharist

import com.boukharist.data.local.AppDatabase
import com.boukharist.data.local.datasources.BmrLocalDataSource
import com.boukharist.data.local.datasources.UserLocalDataSource
import com.boukharist.data.local.mapper.BmrDtoToDomainMapper
import com.boukharist.data.local.mapper.UserDtoToDataMapper
import com.boukharist.data.local.mapper.UserDtoToDomainMapper
import com.boukharist.data.memory.datasource.InMemoryRegistrationFormDataSource
import com.boukharist.data.memory.datasource.InMemoryRegistrationFormDataSourceImpl
import com.boukharist.data.remote.datasources.BmrRemoteDataSource
import com.boukharist.data.remote.datasources.BmrRemoteDataSourceFakeImpl
import com.boukharist.data.remote.datasources.UserFakeRemoteDataSourceImpl
import com.boukharist.data.remote.datasources.UserRemoteDataSource
import com.boukharist.data.remote.mapper.BmrRequestMapper
import com.boukharist.data.remote.mapper.BmrResponseMapper
import com.boukharist.data.remote.mapper.UserRequestMapper
import com.boukharist.data.repositories.BmrRepositoryImpl
import com.boukharist.data.repositories.InMemoryRegistrationFormRepositoryImpl
import com.boukharist.data.repositories.UserRepositoryImpl
import com.boukharist.data.utils.DateHelper
import com.boukharist.domain.repository.BmrRepository
import com.boukharist.domain.repository.InMemoryRegistrationFormRepository
import com.boukharist.domain.repository.UserRepository
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
        viewModel { (UserRegistrationBasicInfoViewModel(get(), get())) }
        viewModel { UserRegistrationSecondaryViewModel(get(), get(), get()) }
        viewModel { UserRegistrationViewModel(get()) }

        //useCase
        scoped<UserRegistrationFormUseCase> { UserRegistrationFormUseCaseImpl(get(), get()) }

        //repos
        scoped<InMemoryRegistrationFormRepository> { InMemoryRegistrationFormRepositoryImpl(get()) }

        //dataSource
        scoped<InMemoryRegistrationFormDataSource> { InMemoryRegistrationFormDataSourceImpl() }

        //utils
        scoped<RegistrationRouter> { RegistrationRouterImpl(androidContext()) }
        scoped<DateProvider> { DateProviderImpl() }

        //repo
        scoped<UserRepository> { UserRepositoryImpl(get(),get(),get(),get(),get()) }
        scoped<BmrRepository> { BmrRepositoryImpl(get(),get(),get(),get(),get()) }

        //datasource
        scoped<UserRemoteDataSource> { UserFakeRemoteDataSourceImpl() }
        scoped<BmrRemoteDataSource> { BmrRemoteDataSourceFakeImpl() }
        scoped<BmrLocalDataSource> { get<AppDatabase>().createBmrLocalDataSource() }
        scoped<UserLocalDataSource> { get<AppDatabase>().createUserLocalDataSource() }
        factory { AppDatabase.getInstance(get()) }

        //mapper
        scoped { UserDtoToDataMapper(get()) }
        scoped { UserRequestMapper(get()) }
        scoped { UserDtoToDomainMapper(get()) }
        scoped { BmrRequestMapper(get()) }
        scoped { BmrDtoToDomainMapper() }
        scoped { BmrResponseMapper() }

        scoped { DateHelper() }
    }
}