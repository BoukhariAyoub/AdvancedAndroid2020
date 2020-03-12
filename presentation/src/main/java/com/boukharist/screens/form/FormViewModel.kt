package com.boukharist.screens.form

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boukharist.domains.entities.User
import com.boukharist.domains.usecase.FormUseCase
import com.boukharist.domains.usecase.InfoUseCase
import com.boukharist.mvarchi.FormFields
import com.boukharist.screens.info.DateHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FormViewModel(
    private val useCase: FormUseCase,
    private val dateHelper: DateHelper
) : ViewModel() {

    val birthDateObservable = ObservableField<String>()
    val firstNameObservable = ObservableField<String>()
    val lastNameObservable = ObservableField<String>()

    fun onValidateClicked() {
        viewModelScope.launch {
            val formField = FormFields(firstNameObservable.get()!!, lastNameObservable.get()!!, birthDateObservable.get()!!)
            //saveUser
            withContext(Dispatchers.IO) { useCase.saveUser(formField.toUser()) }
        }
    }

    private fun FormFields.toUser(): User {
        return User(
            firstName = firstName,
            lastName = lastName,
            birthDate = dateHelper.getDateFromString(birthDate)
        )
    }
}