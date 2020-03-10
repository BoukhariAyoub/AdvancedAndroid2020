package com.boukharist.presentation.modernandroiddevelopment.screens.registration.basicinfo

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.boukharist.domain.UserRegistrationForm
import com.boukharist.domain.usecase.UserRegistrationFormUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

class UserRegistrationBasicInfoViewModel(private val formUseCase: UserRegistrationFormUseCase,
                                         private val dateProvider: DateProvider) : ViewModel() {

    init {
        getFormInfoState()
    }

    val isMale = ObservableField<Boolean>()
    val birthDate = ObservableField<String>()
    val height = ObservableField<String>()
    val weight = ObservableField<String>()

    private fun getFormInfoState() {
        viewModelScope.launch {
            formUseCase.getRegistrationForm().collect {
                isMale.set(it.isMale)
                birthDate.set(dateProvider.dateToString(it.birthDate))
                height.set(it.height?.toString())
                weight.set(it.weight?.toString())
            }
        }
    }

    fun saveInfo() {
        val basicInfoFrom = UserRegistrationForm(
            isMale = isMale.get(),
            birthDate = dateProvider.stringToDate(birthDate.get()),
            height = height.get()?.toFloatOrNull(),
            weight = weight.get()?.toFloatOrNull()
        )
        Timber.i("basicInfoFrom = $basicInfoFrom")
        formUseCase.setRegistrationForm(basicInfoFrom)
    }
}