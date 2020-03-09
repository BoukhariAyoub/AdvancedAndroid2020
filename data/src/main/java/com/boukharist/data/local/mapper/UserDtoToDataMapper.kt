package com.boukharist.data.local.mapper

import com.boukharist.data.local.models.UserDto
import com.boukharist.data.remote.models.UserResponse
import com.boukharist.data.utils.DateHelper
import com.boukharist.domain.model.ActivityType
import com.boukharist.domain.model.Gender
import com.boukharist.domain.model.Goal
import com.boukharist.domain.model.User
import java.util.*


class UserDtoToDataMapper(private val dateHelper: DateHelper) : (User) -> UserDto {

    override fun invoke(user: User): UserDto {
        return UserDto(
            id = UUID.randomUUID().toString(),
            gender = user.gender.toIndex(),
            birthDate = user.birthDate.toTimeStamp(),
            height = user.height,
            weight = user.weight,
            activityType = user.activityType.toIndex(),
            goal = user.goal.toIndex()
        )
    }

    private fun Gender.toIndex(): Int {
        return when (this) {
            Gender.Male -> 0
            Gender.Female -> 1
        }
    }

    private fun ActivityType.toIndex(): Int {
        return when (this) {
            ActivityType.LOW -> 0
            ActivityType.MEDIUM -> 1
            ActivityType.HIGH -> 2
        }
    }

    private fun Goal.toIndex(): Int {
        return when (this) {
            Goal.LOOSE -> 0
            Goal.MAINTAIN -> 1
            Goal.GAIN -> 2
        }
    }

    private fun Date.toTimeStamp(): Long {
        return dateHelper.dateToTimeStamp(this)
    }

}



