package com.boukharist.domain.model

import java.lang.Exception


//BMR
sealed class BmrException : Exception()
class BmrComputationException(val reason: String) : BmrException()


//USER
sealed class UserException : Exception()
object UserNotFoundException : UserException()
data class UserRegistrationException(val reason: String) : UserException()
