package com.boukharist.modernandroiddevelopment

import com.boukharist.data.datasource.inmemory.user.InMemoryUserDataSource
import com.boukharist.data.datasource.inmemory.user.InMemoryUserDataSourceImpl
import com.boukharist.data.repository.UserRepoImpl
import com.boukharist.domains.repositories.UserRepo
import com.boukharist.domains.usecase.FormUseCase
import com.boukharist.domains.usecase.FormUseCaseImpl
import com.boukharist.domains.usecase.InfoUseCase
import com.boukharist.domains.usecase.InfoUseCaseImpl
import com.boukharist.screens.MainActivity
import com.boukharist.screens.form.FormViewModel
import com.boukharist.screens.info.DateHelper
import com.boukharist.screens.info.DateHelperImpl
import com.boukharist.screens.info.InfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


val mainModule = module {
    scope(named<MainActivity>()) {
        //ViewModel
        viewModel { InfoViewModel(get(), get()) }
        viewModel { FormViewModel(get(), get()) }

        //UseCase
        scoped<InfoUseCase> { InfoUseCaseImpl(get()) }
        scoped<FormUseCase> { FormUseCaseImpl(get()) }

        //REPO
        scoped<UserRepo> { UserRepoImpl(get()) }

        //DataSource
        scoped<InMemoryUserDataSource> { InMemoryUserDataSourceImpl() }
    }

    //Miscellaneous
    factory<DateHelper> { DateHelperImpl() }
}
