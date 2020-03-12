package com.boukharist.screens.form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boukharist.mvarchi.FormFields
import kotlinx.coroutines.launch

class FormViewModel() : ViewModel() {

    val formLiveData = MutableLiveData<FormFields>()

    fun onFormTextChanged(firstName: String, lastName: String, birthDate: String) {
        formLiveData.value = FormFields(firstName, lastName, birthDate)
    }

    fun onValidateClicked(firstName: String, lastName: String, birthDate: String) {
        viewModelScope.launch {

        }
    }
}