package com.boukharist.data.memory.models

import com.boukharist.domain.model.ActivityType
import com.boukharist.domain.model.Goal
import java.util.Date

data class InMemoryUserRegistrationForm(
    val isMale: Boolean? = null,
    val birthDate: Date? = null,
    val height: Float? = null,
    val weight: Float? = null,
    val activityTypeIndex: ActivityType? = null,
    val goalIndex: Goal? = null,
    val currentPage: Int? = null,
    val hasUserSubmitted: Boolean = false
)