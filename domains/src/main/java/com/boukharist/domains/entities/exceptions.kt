package com.boukharist.domains.entities

sealed class UserException : Throwable()
object UserNotFoundException : UserException()
class UserRepositoryDownException(val reason: String) : UserException()