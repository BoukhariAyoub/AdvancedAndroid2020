package com.boukharist.presentation.modernandroiddevelopment.screens.healthinfo

import android.util.Log
import androidx.lifecycle.*
import com.boukharist.domain.model.User
import com.boukharist.domain.usecase.UserUseCase
import com.boukharist.presentation.modernandroiddevelopment.screens.registration.basicinfo.UserRegistrationBasicInfoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HealthInfoViewModel(private val useCase: UserUseCase) : ViewModel() {

    val userLiveData = MutableLiveData<User>()

    fun observeUser() {
        viewModelScope.launch {
            useCase.getUser()
                .take(1)
                .onEach { Log.i("InMemory","Got User in Health") }
                .flowOn(Dispatchers.IO)
                .collect {
                    userLiveData.value = it.getOrNull()
                }
        }
    }
}