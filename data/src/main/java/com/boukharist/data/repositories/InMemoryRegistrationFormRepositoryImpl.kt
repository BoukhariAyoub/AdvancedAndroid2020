package com.boukharist.data.repositories

import com.boukharist.data.memory.datasource.InMemoryRegistrationFormDataSource
import com.boukharist.data.memory.models.InMemoryUserRegistrationForm
import com.boukharist.domain.UserRegistrationForm
import com.boukharist.domain.model.User
import com.boukharist.domain.repository.InMemoryRegistrationFormRepository
import kotlinx.coroutines.flow.*

class InMemoryRegistrationFormRepositoryImpl(private val dataSource: InMemoryRegistrationFormDataSource) : InMemoryRegistrationFormRepository {

    override fun getRegistrationForm(): Flow<UserRegistrationForm> {
        return dataSource.getRegistrationForm().map { it.toDomain() }
    }

    override suspend fun setRegistrationForm(newForm: UserRegistrationForm) {
        dataSource.setRegistrationForm(newForm.toData())
    }

    private fun InMemoryUserRegistrationForm.toDomain(): UserRegistrationForm {
        return UserRegistrationForm(
            isMale = isMale,
            birthDate = birthDate,
            height = height,
            weight = weight,
            activityType = activityTypeIndex,
            goal = goalIndex,
            currentPage = currentPage,
            hasUserSubmitted = hasUserSubmitted
        )
    }

    private fun UserRegistrationForm.toData(): InMemoryUserRegistrationForm {
        return InMemoryUserRegistrationForm(
            isMale = isMale,
            birthDate = birthDate,
            height = height,
            weight = weight,
            activityTypeIndex = activityType,
            goalIndex = goal,
            currentPage = currentPage,
            hasUserSubmitted = hasUserSubmitted
        )
    }
}