package com.boukharist.presentation.modernandroiddevelopment.screens.registration.secondaryInfo

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boukharist.domain.UserRegistrationForm
import com.boukharist.domain.model.ActivityType
import com.boukharist.domain.model.Goal
import com.boukharist.domain.usecase.UserRegistrationFormUseCase
import com.boukharist.domain.usecase.UserUseCase
import com.boukharist.presentation.modernandroiddevelopment.screens.registration.RegistrationRouter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class UserRegistrationSecondaryViewModel(
    private val formUseCase: UserRegistrationFormUseCase,
    private val userUseCase: UserUseCase,
    private val router: RegistrationRouter
) : ViewModel() {

    init {
        getFormInfoState()
    }

    val goalIndex = ObservableField<Int>(1)
    val activityTypeIndex = ObservableField<Int>(2)

    fun onActivityTypeChanged(activityType: ActivityType) {
        val pos = activityType.ordinal
        if (activityTypeIndex.get() != pos) {
            activityTypeIndex.set(pos)
        }
    }

    fun onGoalChanged(goal: Goal) {
        val pos = goal.ordinal
        if (goalIndex.get() != pos) {
            goalIndex.set(pos)
        }
    }

    private fun getFormInfoState() {
        viewModelScope.launch {
            formUseCase.getRegistrationForm()
                .flowOn(Dispatchers.IO)
                .collect {
                    it.goal?.let { goal -> onGoalChanged(goal) }
                    it.activityType?.let { activity -> onActivityTypeChanged(activity) }
                }
        }
    }

    fun onValidateClicked() {
        viewModelScope.launch {
            val activityType = activityTypeIndex.get()?.let { ActivityType.values()[it] }
            val goal = goalIndex.get()?.let { Goal.values()[it] }
            val secondaryInfoForm = UserRegistrationForm(activityType = activityType, goal = goal, hasUserSubmitted = true)
            formUseCase.setRegistrationForm(secondaryInfoForm)
            router.navigateToHealthInfo()
        }
    }

}