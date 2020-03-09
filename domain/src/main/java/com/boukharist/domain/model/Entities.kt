package com.boukharist.domain.model

import java.util.*


data class User(
    val gender: Gender,
    val birthDate: Date,
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

data class HealthInfo(val bmr: Double, val tdee: Double, val caloriesIntake: Double)