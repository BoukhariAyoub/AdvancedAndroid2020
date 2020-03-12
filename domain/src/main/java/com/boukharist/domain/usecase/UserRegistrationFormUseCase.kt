package com.boukharist.domain.usecase

import com.boukharist.domain.UserRegistrationForm
import com.boukharist.domain.model.Gender
import com.boukharist.domain.model.User
import com.boukharist.domain.repository.InMemoryRegistrationFormRepository
import com.boukharist.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import sun.rmi.runtime.Log

interface UserRegistrationFormUseCase {
    fun getRegistrationForm(): Flow<UserRegistrationForm>

    suspend fun setRegistrationForm(newForm: UserRegistrationForm)
}

class UserRegistrationFormUseCaseImpl(
    private val formRepository: InMemoryRegistrationFormRepository,
    private val userRepository: UserRepository
) : UserRegistrationFormUseCase {

    override fun getRegistrationForm(): Flow<UserRegistrationForm> {
        return formRepository.getRegistrationForm()
            .onEach { it.registerUserIfFormComplete() }
    }

    private suspend fun UserRegistrationForm.registerUserIfFormComplete() {
        if(hasUserSubmitted){
            checkNotNull(isMale)
            checkNotNull(birthDate)
            checkNotNull(height)
            checkNotNull(weight)
            checkNotNull(activityType)
            checkNotNull(goal)
            val user = User(gender = if (isMale) Gender.Male else Gender.Female, birthDate = birthDate, height = height, weight = weight, activityType = activityType, goal = goal)
            userRepository.registerUser(user)
        }
    }

    override suspend fun setRegistrationForm(newForm: UserRegistrationForm) {
        formRepository.setRegistrationForm(newForm)
    }
}