package com.boukharist.data.remote.models

data class UserResponse(
    val gender: Int,
    val birthDate: Long,
    val height: Float,
    val weight: Float,
    val activityType: Int,
    val goal: Int
)

data class UserRequest(
    val gender: Int,
    val birthDate: Long,
    val height: Float,
    val weight: Float,
    val activityType: Int,
    val goal: Int
)