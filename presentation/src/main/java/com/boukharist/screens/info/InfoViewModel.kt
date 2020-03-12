package com.boukharist.screens.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boukharist.domains.entities.*
import com.boukharist.domains.usecase.InfoUseCase
import com.boukharist.mvarchi.Color
import com.boukharist.mvarchi.DisplayableUser
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class InfoViewModel(
    private val infoUseCase: InfoUseCase,
    private val dateHelper: DateHelper
) : ViewModel() {

    val userLiveData = MutableLiveData<DisplayableUser>()
    val errorLiveDate = MutableLiveData<String>()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            infoUseCase.getUser()
                .collect { callResult ->
                    when (callResult) {
                        is Success -> userLiveData.value = callResult.value.toDisplayableUser()
                        is Failure -> {
                            errorLiveDate.value = callResult.reason.toErrorString()
                        }
                    }
                }
        }
    }

    private fun User.toDisplayableUser(): DisplayableUser {
        val age = this.birthDate.toAge()
        val color = if (age < 30) Color.BLUE else Color.RED
        return DisplayableUser(fullName = "$firstName $lastName", age = age, ageColor = color)
    }


    private fun Date.toAge(): Int {
        return dateHelper.computeAge(this)

    }

    private fun UserException.toErrorString(): String {
        return when (this) {
            UserNotFoundException -> "We did not find any User"
            is UserRepositoryDownException -> "Network is Down"
        }
    }

}

