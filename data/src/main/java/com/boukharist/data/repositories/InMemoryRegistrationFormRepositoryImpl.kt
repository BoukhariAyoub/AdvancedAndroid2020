package com.boukharist.data.repositories

import com.boukharist.data.memory.datasource.InMemoryRegistrationFormDataSource
import com.boukharist.data.memory.models.InMemoryUserRegistrationForm
import com.boukharist.domain.UserRegistrationForm
import com.boukharist.domain.repository.InMemoryRegistrationFormRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class InMemoryRegistrationFormRepositoryImpl(private val dataSource: InMemoryRegistrationFormDataSource) : InMemoryRegistrationFormRepository {

    override fun getRegistrationForm(): Flow<UserRegistrationForm> {
        return dataSource.getRegistrationForm().map { it.toDomain() }
    }

    override fun setRegistrationForm(newForm: UserRegistrationForm) {
        dataSource.setRegistrationForm(newForm.toData())
    }

    private fun InMemoryUserRegistrationForm.toDomain(): UserRegistrationForm {
        return UserRegistrationForm(
            isMale = isMale,
            birthDate = birthDate,
            height = height,
            weight = weight,
            activityType = activityTypeIndex,
            goal = goalIndex
        )
    }

    private fun UserRegistrationForm.toData(): InMemoryUserRegistrationForm {
        return InMemoryUserRegistrationForm(
            isMale = isMale,
            birthDate = birthDate,
            height = height,
            weight = weight,
            activityTypeIndex = activityType,
            goalIndex = goal
        )
    }
}