package com.boukharist.domain.repository

import com.boukharist.domain.model.CallResult
import com.boukharist.domain.model.User
import com.boukharist.domain.model.UserNotFoundException
import com.boukharist.domain.model.UserRegistrationException
import kotlinx.coroutines.flow.Flow


interface UserRepository {
    fun registerUser(user: User): CallResult<Unit, UserRegistrationException>

    fun getUser(): Flow<CallResult<User, UserNotFoundException>>
}

