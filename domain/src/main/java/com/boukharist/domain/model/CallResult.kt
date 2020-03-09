package com.boukharist.domain.model


sealed class CallResult<out S, out F : Throwable> {
    companion object {
        fun <S> success(value: S) = Success(value)
        fun <F : Throwable> failure(reason: F) = Failure(reason)
    }

    /**
     * Returns `true` if this instance represents successful outcome.
     * In this case [isFailure] returns `false`.
     */
    val isSuccess: Boolean get() = this !is Failure<*>

    /**
     * Returns `true` if this instance represents failed outcome.
     * In this case [isSuccess] returns `false`.
     */
    val isFailure: Boolean get() = this is Failure

    fun getOrNull(): S? = when (this) {
        is Failure -> null
        is Success -> value
    }

    /**
     * Returns the encapsulated exception if this instance represents [failure][isFailure] or `null`
     * if it is [success][isSuccess].
     *
     * This function is shorthand for `fold(onSuccess = { null }, onFailure = { it })` (see [fold]).
     */
    fun exceptionOrNull(): Throwable? = when (this) {
        is Failure -> reason
        is Success -> null
    }
}

data class Success<out S>(val value: S) : CallResult<S, Nothing>()
data class Failure<out F : Throwable>(val reason: F) : CallResult<Nothing, F>()