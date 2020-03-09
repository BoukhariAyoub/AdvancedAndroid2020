package com.boukharist.data.remote.models

data class BmrResponse(val bmr: Double, val tdee: Double, val caloriesIntake: Double)

data class BmrRequest(
    val gender: Int,
    val age: Int,
    val height: Float,
    val weight: Float,
    val activityType: Int,
    val goal: Int
)