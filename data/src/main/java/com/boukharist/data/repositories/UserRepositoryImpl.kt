package com.boukharist.data.repositories

import android.util.Log
import com.boukharist.data.remote.datasources.UserRemoteDataSource
import com.boukharist.data.local.datasources.UserLocalDataSource
import com.boukharist.data.local.mapper.UserDtoToDataMapper
import com.boukharist.data.local.mapper.UserDtoToDomainMapper
import com.boukharist.data.remote.mapper.UserRequestMapper
import com.boukharist.domain.model.CallResult
import com.boukharist.domain.model.User
import com.boukharist.domain.model.UserNotFoundException
import com.boukharist.domain.model.UserRegistrationException
import com.boukharist.domain.repository.UserRepository
import kotlinx.coroutines.flow.*
import java.io.IOException

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource,
    private val userRequestMapper: UserRequestMapper,
    private val userDtoToDataMapper: UserDtoToDataMapper,
    private val userDtoToDomainMapper: UserDtoToDomainMapper
) : UserRepository {

    override suspend fun registerUser(user: User): CallResult<Unit, UserRegistrationException> {
        return try {
            Log.i("InMemory", "Save user")
            val userRequest = user.let(userRequestMapper)
            // remoteDataSource.registerUser(userRequest)
            localDataSource.setLoggedInUser(user.let(userDtoToDataMapper))
            localDataSource.findCurrentUser().take(1).collect { Log.i("InMemory", "usersList : $it") }
            CallResult.success(Unit)
        } catch (ex: IOException) {
            CallResult.failure(UserRegistrationException(ex.localizedMessage ?: "Uknown"))
        }
    }

    override fun getUser(): Flow<CallResult<User, UserNotFoundException>> {
        Log.i("InMemory", "getUser Called")
        return localDataSource.findCurrentUser()
            .map { it.first() }
            .map { userDto ->
            Log.i("InMemory", "Got user")
            if (userDto != null) {
                CallResult.success(userDto.let(userDtoToDomainMapper))
            } else {
                CallResult.failure(UserNotFoundException)
            }
        }
    }
}