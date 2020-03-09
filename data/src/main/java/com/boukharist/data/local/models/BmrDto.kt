package com.boukharist.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bmr")
data class BmrDto(
    @PrimaryKey
    val bmrRequestHash: Int,
    val bmr: Double,
    val tdee: Double,
    val caloriesIntake: Double
)
