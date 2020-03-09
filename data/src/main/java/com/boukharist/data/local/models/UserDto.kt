package com.boukharist.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserDto(
    @PrimaryKey
    val id : String,
    val gender: Int,
    val birthDate: Long,
    val height: Float,
    val weight: Float,
    val activityType: Int,
    val goal: Int
)
