package com.boukharist

import com.boukharist.data.local.AppDatabase
import com.boukharist.data.local.datasources.BmrLocalDataSource
import com.boukharist.data.local.datasources.UserLocalDataSource
import com.boukharist.data.local.mapper.BmrDtoToDomainMapper
import com.boukharist.data.local.mapper.UserDtoToDataMapper
import com.boukharist.data.local.mapper.UserDtoToDomainMapper
import com.boukharist.data.remote.datasources.BmrRemoteDataSource
import com.boukharist.data.remote.datasources.BmrRemoteDataSourceFakeImpl
import com.boukharist.data.remote.datasources.UserFakeRemoteDataSourceImpl
import com.boukharist.data.remote.datasources.UserRemoteDataSource
import com.boukharist.data.remote.mapper.BmrRequestMapper
import com.boukharist.data.remote.mapper.BmrResponseMapper
import com.boukharist.data.remote.mapper.UserRequestMapper
import com.boukharist.data.repositories.BmrRepositoryImpl
import com.boukharist.data.repositories.UserRepositoryImpl
import com.boukharist.data.utils.DateHelper
import com.boukharist.domain.repository.BmrRepository
import com.boukharist.domain.repository.UserRepository
import com.boukharist.domain.usecase.UserUseCase
import com.boukharist.domain.usecase.UserUseCaseImpl
import com.boukharist.presentation.modernandroiddevelopment.screens.healthinfo.HealthInfoViewModel
import com.boukharist.presentation.modernandroiddevelopment.screens.registration.UserRegistrationActivity
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val scopes = module {
    single(named<UserRegistrationActivity>()) { getKoin().getOrCreateScope("registration", named<UserRegistrationActivity>()) }
}

val module = module {
    viewModel { HealthInfoViewModel(get()) }
    factory<UserUseCase> { UserUseCaseImpl(get(),get()) }


    //repo
    factory<UserRepository> { UserRepositoryImpl(get(),get(),get(),get(),get()) }
    factory<BmrRepository> { BmrRepositoryImpl(get(),get(),get(),get(),get()) }

    //datasource
    factory<UserRemoteDataSource> { UserFakeRemoteDataSourceImpl() }
    factory<BmrRemoteDataSource> { BmrRemoteDataSourceFakeImpl() }
    factory<BmrLocalDataSource> { get<AppDatabase>().createBmrLocalDataSource() }
    factory<UserLocalDataSource> { get<AppDatabase>().createUserLocalDataSource() }

    //move to date
    single { AppDatabase.getInstance(get()) }

    //mapper
    factory { UserDtoToDataMapper(get()) }
    factory { UserRequestMapper(get()) }
    factory { UserDtoToDomainMapper(get()) }
    factory { BmrRequestMapper(get()) }
    factory { BmrDtoToDomainMapper() }
    factory { BmrResponseMapper() }

    factory { DateHelper() }
}

val applicationModules = registrationFormModule + scopes + module
