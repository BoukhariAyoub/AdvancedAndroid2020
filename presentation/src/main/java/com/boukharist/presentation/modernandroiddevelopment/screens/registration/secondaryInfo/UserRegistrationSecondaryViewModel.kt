package com.boukharist.presentation.modernandroiddevelopment.screens.registration.secondaryInfo

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boukharist.domain.UserRegistrationForm
import com.boukharist.domain.model.ActivityType
import com.boukharist.domain.model.Goal
import com.boukharist.domain.usecase.UserRegistrationFormUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserRegistrationSecondaryViewModel(
    private val formUseCase: UserRegistrationFormUseCase
) : ViewModel() {

    init {
        getFormInfoState()
    }

    val goalIndex = ObservableField<Int>()
    val activityTypeIndex = ObservableField<Int>()

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
            formUseCase.getRegistrationForm().collect {
                it.goal?.let { goal -> onGoalChanged(goal) }
                it.activityType?.let { activity -> onActivityTypeChanged(activity) }
            }
        }
    }

    fun saveInfo() {
        val activityType = activityTypeIndex.get()?.let { ActivityType.values()[it] }
        val goal = goalIndex.get()?.let { Goal.values()[it] }
        val secondaryInfoForm = UserRegistrationForm(activityType = activityType, goal = goal)
        formUseCase.setRegistrationForm(secondaryInfoForm)
    }
}