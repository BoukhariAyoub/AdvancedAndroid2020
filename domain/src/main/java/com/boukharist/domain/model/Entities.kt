package com.boukharist.domain.model

import java.time.LocalDate

data class User(
    val gender: Gender,
    val birthDate: LocalDate,
    val height: Float,
    val weight: Float,
    val activityType: ActivityType,
    val goal: Goal
)

enum class Gender {
    Male, Female
}

enum class ActivityType {
    LOW, MEDIUM, HIGH
}

enum class Goal {
    LOOSE, MAINTAIN, GAIN
}

data class Bmr(val bmr: String, val tdee: String)