package com.boukharist.domain

import com.boukharist.domain.model.ActivityType
import com.boukharist.domain.model.Goal
import java.util.*


data class UserRegistrationForm(
    val isMale: Boolean? = null,
    val birthDate: Date? = null,
    val height: Float? = null,
    val weight: Float? = null,
    val activityType: ActivityType? = null,
    val goal: Goal? = null,
    val currentPage: Int? = null,
    val hasUserSubmitted: Boolean = false
)