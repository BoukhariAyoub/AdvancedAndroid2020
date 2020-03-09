package com.boukharist.data.remote.mapper

import com.boukharist.data.remote.models.BmrRequest
import com.boukharist.data.utils.DateHelper
import com.boukharist.domain.model.ActivityType
import com.boukharist.domain.model.ActivityType.*
import com.boukharist.domain.model.Gender
import com.boukharist.domain.model.Gender.*
import com.boukharist.domain.model.Goal
import com.boukharist.domain.model.Goal.*
import com.boukharist.domain.model.User
import java.util.*

class BmrRequestMapper(private val dateHelper: DateHelper) : (User) -> BmrRequest {
    override fun invoke(user: User): BmrRequest {
        return BmrRequest(
            gender = user.gender.toIndex(),
            age = user.birthDate.toAge(),
            height = user.height,
            weight = user.weight,
            activityType = user.activityType.toIndex(),
            goal = user.goal.toIndex()
        )
    }

    private fun Gender.toIndex(): Int {
        return when (this) {
            Male -> 0
            Female -> 1
        }
    }

    private fun ActivityType.toIndex(): Int {
        return when (this) {
            LOW -> 0
            MEDIUM -> 1
            HIGH -> 2
        }
    }

    private fun Goal.toIndex(): Int {
        return when (this) {
            LOOSE -> 0
            MAINTAIN -> 1
            GAIN -> 2
        }
    }

    private fun Date.toAge(): Int {
        val now = dateHelper.now()
        return dateHelper.getAgeFrom(now, this)
    }


}



