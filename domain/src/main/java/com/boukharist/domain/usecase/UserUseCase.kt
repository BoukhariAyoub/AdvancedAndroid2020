package com.boukharist.domain.usecase

import com.boukharist.domain.model.*
import com.boukharist.domain.repository.BmrRepository
import com.boukharist.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun getBmr(user: User): Flow<CallResult<HealthInfo, BmrException>>
    fun registerUser(user: User): CallResult<Unit, UserException>
    fun getUser(): Flow<CallResult<User, UserException>>
}

class UserUseCaseImpl(
    private val userRepository: UserRepository,
    private val bmrRepository: BmrRepository
) : UserUseCase {

    override fun getBmr(user: User): Flow<CallResult<HealthInfo, BmrException>> {
        return bmrRepository.computeBmr(user)
    }

    override fun registerUser(user: User) = userRepository.registerUser(user)

    override fun getUser(): Flow<CallResult<User, UserException>> {
        return userRepository.getUser()
    }
}