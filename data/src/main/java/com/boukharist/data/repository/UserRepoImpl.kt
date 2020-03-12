package com.boukharist.data.repository

import com.boukharist.data.datasource.inmemory.user.InMemoryUser
import com.boukharist.data.datasource.inmemory.user.InMemoryUserDataSource
import com.boukharist.domains.entities.CallResult
import com.boukharist.domains.entities.User
import com.boukharist.domains.entities.UserException
import com.boukharist.domains.entities.UserNotFoundException
import com.boukharist.domains.repositories.UserRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onErrorReturn
import java.util.*

class UserRepoImpl(private val inMemoryUserDataSource: InMemoryUserDataSource) : UserRepo {

    override fun getUser(): Flow<CallResult<User, UserException>> {
        return inMemoryUserDataSource.getUserFormMemory()
            .catch { CallResult.failure(UserNotFoundException) }
            .map { inMemory -> inMemory.toDomainUser() }
            .map { CallResult.success(it) }
    }

    override suspend fun setUser(user: User) {
        inMemoryUserDataSource.setUser(user.toInMemoryUser())
    }

    private fun InMemoryUser.toDomainUser(): User {
        return User(
            firstName,
            lastName,
            Date(birthDate)
        )
    }

    private fun User.toInMemoryUser(): InMemoryUser {
        return InMemoryUser(
            firstName,
            lastName,
            birthDate.time
        )
    }
}