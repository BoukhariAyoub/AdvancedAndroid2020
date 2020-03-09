package com.boukharist.modernandroiddevelopment.screens.registration

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class UserRegistrationSecondaryViewModel : ViewModel() {

    val goalIndex = ObservableField<Int>()
    val activityTypeIndex = ObservableField<Int>()

    fun onActivityTypeChanged(pos: Int) {
        if(activityTypeIndex.get() != pos) {
            activityTypeIndex.set(pos)
        }
    }

    fun onGoalChanged(pos: Int) {
        if(goalIndex.get() != pos) {
            goalIndex.set(pos)
        }
    }
}