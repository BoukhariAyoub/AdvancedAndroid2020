package com.boukharist.presentation.modernandroiddevelopment.screens.registration

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserRegistrationViewModel : ViewModel() {

    val currentViewIndex = MutableLiveData<Int>()

    fun setCurrentItemIndex(pos: Int) {
        if (currentViewIndex.value != pos) {
            currentViewIndex.value = pos
        }
    }

    fun navigateToSecondaryRegistration() {
        currentViewIndex.value = 1
    }

    fun navigateToHealthInfo() {
       // registrationRouter.navigateToHealthInfo()
    }
}

class UserRegistrationViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val router = RegistrationRouterImpl(context)
        return UserRegistrationViewModel() as T
    }
}