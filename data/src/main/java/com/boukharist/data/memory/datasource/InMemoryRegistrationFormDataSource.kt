package com.boukharist.data.memory.datasource

import com.boukharist.data.memory.models.InMemoryUserRegistrationForm
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

interface InMemoryRegistrationFormDataSource {
    fun getRegistrationForm(): Flow<InMemoryUserRegistrationForm>
    fun setRegistrationForm(newForm: InMemoryUserRegistrationForm)
}

class InMemoryRegistrationFormDataSourceImpl : InMemoryRegistrationFormDataSource {

    private val registrationFormChannel = BroadcastChannel<InMemoryUserRegistrationForm>(Channel.CONFLATED)
    private var lastRegistrationForm = InMemoryUserRegistrationForm()

    override fun getRegistrationForm(): Flow<InMemoryUserRegistrationForm> {
        return registrationFormChannel.asFlow()
    }

    override fun setRegistrationForm(newForm: InMemoryUserRegistrationForm) {
        lastRegistrationForm = getUpdatedForm(newForm)
        this.registrationFormChannel.offer(lastRegistrationForm)
    }

    private fun getUpdatedForm(newForm: InMemoryUserRegistrationForm): InMemoryUserRegistrationForm {
        return lastRegistrationForm.copy(
            isMale = newForm.isMale ?: lastRegistrationForm.isMale,
            birthDate = newForm.birthDate ?: lastRegistrationForm.birthDate,
            height = newForm.height ?: lastRegistrationForm.height,
            weight = newForm.weight ?: lastRegistrationForm.weight,
            activityTypeIndex = newForm.activityTypeIndex ?: lastRegistrationForm.activityTypeIndex,
            goalIndex = newForm.goalIndex ?: lastRegistrationForm.goalIndex
        )
    }
}