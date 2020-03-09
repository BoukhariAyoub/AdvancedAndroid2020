package com.boukharist.data.remote.datasources

import com.boukharist.data.remote.models.UserRequest
import okio.IOException
import kotlin.random.Random

interface UserRemoteDataSource {
    @Throws(IOException::class)
    fun registerUser(userRequest: UserRequest)
}

class UserFakeRemoteDataSourceImpl : UserRemoteDataSource {
    override fun registerUser(userRequest: UserRequest) {
        Thread.sleep(1000)
        val shouldThrowError = Random.nextBoolean()
        if (shouldThrowError) {
            throw IOException()
        }
    }
}