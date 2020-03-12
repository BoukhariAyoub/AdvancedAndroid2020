package com.boukharist.domains.usecase

import com.boukharist.domains.entities.CallResult
import com.boukharist.domains.entities.User
import com.boukharist.domains.entities.UserException
import com.boukharist.domains.repositories.UserRepo
import kotlinx.coroutines.flow.Flow

interface InfoUseCase {
    fun getUser(): Flow<CallResult<User, UserException>>
}

class InfoUseCaseImpl(private val repository: UserRepo) : InfoUseCase {

    override fun getUser(): Flow<CallResult<User, UserException>> {
       return repository.getUser()
    }
}