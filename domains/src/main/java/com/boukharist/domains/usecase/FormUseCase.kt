package com.boukharist.domains.usecase

import com.boukharist.domains.entities.User
import com.boukharist.domains.repositories.UserRepo

interface FormUseCase {
    suspend fun saveUser(user: User)
}

class FormUseCaseImpl(private val repository: UserRepo) : FormUseCase {

    override suspend fun saveUser(user: User) {
        repository.setUser(user)
    }
}