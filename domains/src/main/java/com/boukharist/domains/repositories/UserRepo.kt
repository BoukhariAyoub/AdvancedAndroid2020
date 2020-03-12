package com.boukharist.domains.repositories

import com.boukharist.domains.entities.CallResult
import com.boukharist.domains.entities.User
import com.boukharist.domains.entities.UserException
import kotlinx.coroutines.flow.Flow

interface UserRepo {
    fun getUser(): Flow<CallResult<User, UserException>>
    suspend fun setUser(user: User)
}

