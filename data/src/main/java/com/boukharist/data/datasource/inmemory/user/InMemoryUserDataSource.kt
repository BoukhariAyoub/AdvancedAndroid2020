package com.boukharist.data.datasource.inmemory.user

import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

interface InMemoryUserDataSource {
    fun getUserFormMemory(): Flow<InMemoryUser>
    suspend fun setUser(inMemoryUser: InMemoryUser)
}


class InMemoryUserDataSourceImpl : InMemoryUserDataSource {

    private val userChannel = ConflatedBroadcastChannel<InMemoryUser>().apply {
        offer(InMemoryUser("firstname", "lastName", 637239829000L))
    }

    override fun getUserFormMemory(): Flow<InMemoryUser> {
        return userChannel.asFlow()
    }

    override suspend fun setUser(inMemoryUser: InMemoryUser) {
        userChannel.offer(inMemoryUser)
    }
}
