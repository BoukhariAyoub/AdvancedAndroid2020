package com.boukharist.domain.repository

import com.boukharist.domain.UserRegistrationForm
import com.boukharist.domain.model.CallResult
import com.boukharist.domain.model.User
import com.boukharist.domain.model.UserNotFoundException
import com.boukharist.domain.model.UserRegistrationException
import kotlinx.coroutines.flow.Flow


interface InMemoryRegistrationFormRepository {

    fun getRegistrationForm(): Flow<UserRegistrationForm>

    suspend fun setRegistrationForm(newForm: UserRegistrationForm)
}

