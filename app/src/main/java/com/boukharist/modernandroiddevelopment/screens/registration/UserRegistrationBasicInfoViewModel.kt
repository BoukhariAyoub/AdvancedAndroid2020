package com.boukharist.modernandroiddevelopment.screens.registration

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class UserRegistrationBasicInfoViewModel : ViewModel() {

    val isMale = ObservableField<Boolean>()
    val birthDate = ObservableField<String>()
    val height = ObservableField<String>()
    val weight = ObservableField<String>()
}