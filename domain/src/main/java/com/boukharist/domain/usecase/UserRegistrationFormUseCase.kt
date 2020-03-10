package com.boukharist.domain.usecase

import com.boukharist.domain.UserRegistrationForm
import com.boukharist.domain.repository.InMemoryRegistrationFormRepository
import kotlinx.coroutines.flow.Flow

interface UserRegistrationFormUseCase {
    fun getRegistrationForm(): Flow<UserRegistrationForm>

    fun setRegistrationForm(newForm: UserRegistrationForm)
}

class UserRegistrationFormUseCaseImpl(private val formRepository: InMemoryRegistrationFormRepository) : UserRegistrationFormUseCase {

    override fun getRegistrationForm(): Flow<UserRegistrationForm> {
        return formRepository.getRegistrationForm()
    }

    override fun setRegistrationForm(newForm: UserRegistrationForm) {
        formRepository.setRegistrationForm(newForm)
    }
}