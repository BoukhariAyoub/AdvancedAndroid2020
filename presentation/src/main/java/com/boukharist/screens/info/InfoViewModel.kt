package com.boukharist.mvarchi.info

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boukharist.mvarchi.Color
import com.boukharist.mvarchi.DisplayableUser
import com.boukharist.mvarchi.IUserApi
import com.boukharist.mvarchi.User
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class InfoViewModel(private val userApi: IUserApi) : ViewModel() {

    val userLiveData = MutableLiveData<DisplayableUser>()

    fun fetchData() {
        viewModelScope.launch {
            userApi.getUser()
                .onEach { Log.i("DEBUG", "$it") }
                .map { it.toDisplayableUser() }
                .collect { displayableUser ->
                    userLiveData.value = displayableUser
                }
        }
    }

    private fun User.toDisplayableUser(): DisplayableUser {
        val now = LocalDateTime.now()
        val age = getAge(now)
        val color = if (age < 30) Color.BLUE else Color.RED
        return DisplayableUser(getFullName(), age, color)
    }
}
