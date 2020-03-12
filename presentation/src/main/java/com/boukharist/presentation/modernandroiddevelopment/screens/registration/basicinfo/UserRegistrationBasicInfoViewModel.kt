package com.boukharist.presentation.modernandroiddevelopment.screens.registration.basicinfo

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boukharist.domain.UserRegistrationForm
import com.boukharist.domain.usecase.UserRegistrationFormUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber

class UserRegistrationBasicInfoViewModel(
    private val formUseCase: UserRegistrationFormUseCase,
    private val dateProvider: DateProvider
) : ViewModel() {

    init {
        getFormInfoState()
    }

    val isMale = ObservableField<Boolean>(true)
    val birthDate = ObservableField<String>("11/07/1990")
    val height = ObservableField<String>("167")
    val weight = ObservableField<String>("13")

    private fun getFormInfoState() {
        viewModelScope.launch {
            formUseCase.getRegistrationForm()
                .flowOn(Dispatchers.IO)
                .collect {
                    Timber.i("InMemory : getFormInfoState ${it.isMale}")
                    it.isMale?.let { isMale.set(it) }
                    it.birthDate?.let { dateProvider.dateToString(it) }
                    it.height?.let { height.set(it.toString()) }
                    it.weight?.let { weight.set(it.toString()) }
                }
        }
    }

    fun onNextClicked() {
        viewModelScope.launch {
            val basicInfoFrom = UserRegistrationForm(
                isMale = isMale.get(),
                birthDate = dateProvider.stringToDate(birthDate.get()?.takeIf { it.isNotEmpty() }),
                height = height.get()?.toFloatOrNull(),
                weight = weight.get()?.toFloatOrNull(),
                currentPage = 1
            )
            formUseCase.setRegistrationForm(basicInfoFrom)
        }
    }
}