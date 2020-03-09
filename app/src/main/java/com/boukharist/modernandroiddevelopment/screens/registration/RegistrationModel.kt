package com.boukharist.modernandroiddevelopment.screens.registration

data class UserRegistrationForm(
    val isMale: Boolean = false,
    val birthDate: String = "",
    val height: String = "",
    val weight: String = "",
    val activityTypeIndex: Int = 0,
    val goalIndex: Int = 0
)