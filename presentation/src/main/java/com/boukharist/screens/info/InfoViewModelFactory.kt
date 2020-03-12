package com.boukharist.mvarchi.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.boukharist.mvarchi.UserApi

class InfoViewModelFactory :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val userApi = UserApi.getInstance()
        return InfoViewModel(userApi) as T
    }
}