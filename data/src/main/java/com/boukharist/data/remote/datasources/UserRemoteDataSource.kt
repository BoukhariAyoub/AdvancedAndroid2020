package com.boukharist.data.remote.datasources

import com.boukharist.data.remote.models.UserRequest
import okio.IOException
import kotlin.random.Random

interface UserRemoteDataSource {
    @Throws(IOException::class)
   suspend fun registerUser(userRequest: UserRequest)
}

class UserFakeRemoteDataSourceImpl : UserRemoteDataSource {
    override suspend fun registerUser(userRequest: UserRequest) {

       // val shouldThrowError = Random.nextBoolean()
     //   if (shouldThrowError) {
    //        throw IOException()
     //   }
    }
}