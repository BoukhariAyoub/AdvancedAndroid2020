package com.boukharist.domain.repository

import com.boukharist.domain.model.*
import java.lang.Exception


interface UserRepository {
    fun registerUser(user: User): CallResult<Unit, UserRegistrationException>

    fun getUser(): CallResult<User, UserNotFoundException>
}

