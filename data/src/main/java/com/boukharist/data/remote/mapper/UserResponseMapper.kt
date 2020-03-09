package com.boukharist.data.remote.mapper

import com.boukharist.data.remote.models.UserResponse
import com.boukharist.data.utils.DateHelper
import com.boukharist.domain.model.ActivityType
import com.boukharist.domain.model.Gender
import com.boukharist.domain.model.Goal
import com.boukharist.domain.model.User
import java.util.*


class UserResponseMapper(private val dateHelper: DateHelper) : (UserResponse) -> User {
    override fun invoke(userRaw: UserResponse): User {
        return User(
            gender = userRaw.gender.toGender(),
            birthDate = userRaw.birthDate.toDate(),
            height = userRaw.height,
            weight = userRaw.weight,
            activityType = userRaw.activityType.toActivity(),
            goal = userRaw.goal.toGoal()
        )
    }

    private fun Int.toGender(): Gender {
        return when (this) {
            0 -> Gender.Male
            1 -> Gender.Female
            else -> Gender.Male
        }
    }

    private fun Long.toDate(): Date {
        return dateHelper.timeStampToDate(this)
    }

    private fun Int.toGoal(): Goal {
        return when (this) {
            0 -> Goal.LOOSE
            1 -> Goal.MAINTAIN
            2 -> Goal.GAIN
            else -> Goal.MAINTAIN
        }
    }

    private fun Int.toActivity(): ActivityType {
        return when (this) {
            0 -> ActivityType.LOW
            1 -> ActivityType.MEDIUM
            2 -> ActivityType.HIGH
            else -> ActivityType.MEDIUM
        }
    }
}



