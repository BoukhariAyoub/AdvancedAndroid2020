package com.boukharist.data.memory.datasource

import android.util.Log
import com.boukharist.data.memory.models.InMemoryUserRegistrationForm
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface InMemoryRegistrationFormDataSource {
    fun getRegistrationForm(): Flow<InMemoryUserRegistrationForm>
    suspend fun setRegistrationForm(newForm: InMemoryUserRegistrationForm)
}

class InMemoryRegistrationFormDataSourceImpl : InMemoryRegistrationFormDataSource {

    private val registrationFormChannel = BroadcastChannel<InMemoryUserRegistrationForm>(Channel.CONFLATED)
    private var lastRegistrationForm = InMemoryUserRegistrationForm()

    override fun getRegistrationForm(): Flow<InMemoryUserRegistrationForm> {
        return registrationFormChannel.asFlow().distinctUntilChangedBy { it }.onEach {
            Log.i("InMemory Changed","$it")
        }
    }

    override suspend fun setRegistrationForm(newForm: InMemoryUserRegistrationForm) {
        lastRegistrationForm = getUpdatedForm(newForm)
        registrationFormChannel.offer(lastRegistrationForm)
    }

    private fun getUpdatedForm(newForm: InMemoryUserRegistrationForm): InMemoryUserRegistrationForm {
        return lastRegistrationForm.copy(
            isMale = newForm.isMale ?: lastRegistrationForm.isMale,
            birthDate = newForm.birthDate ?: lastRegistrationForm.birthDate,
            height = newForm.height ?: lastRegistrationForm.height,
            weight = newForm.weight ?: lastRegistrationForm.weight,
            activityTypeIndex = newForm.activityTypeIndex ?: lastRegistrationForm.activityTypeIndex,
            goalIndex = newForm.goalIndex ?: lastRegistrationForm.goalIndex,
            currentPage = newForm.currentPage ?: lastRegistrationForm.currentPage,
            hasUserSubmitted = newForm.hasUserSubmitted
        )
    }
}