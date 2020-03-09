package com.boukharist.modernandroiddevelopment.screens.registration

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserRegistrationViewModel(private val registrationRouter: RegistrationRouter) : ViewModel() {

    private val formState = MutableLiveData<UserRegistrationForm>()
    val currentViewIndex = MutableLiveData<Int>()

    fun setCurrentItemIndex(pos: Int) {
        if (currentViewIndex.value != pos) {
            currentViewIndex.value = pos
        }
    }

    fun saveInfo(isMale: Boolean, birthDate: String, height: String, weight: String) {
        val currentValue = formState.value ?: UserRegistrationForm()
        formState.value = currentValue.copy(isMale = isMale, birthDate = birthDate, height = height, weight = weight)
    }

    fun saveInfo(activityTypeIndex: Int, goalIndex: Int) {
        val currentValue = formState.value ?: UserRegistrationForm()
        formState.value = currentValue.copy(activityTypeIndex = activityTypeIndex, goalIndex = goalIndex)
    }

    fun navigateToSecondaryRegistration() {
        currentViewIndex.value = 1
    }

    fun navigateToHealthInfo() {
        registrationRouter.navigateToHealthInfo()
    }
}

class UserRegistrationViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val router = RegistrationRouterImpl(context)
        return UserRegistrationViewModel(router) as T
    }
}