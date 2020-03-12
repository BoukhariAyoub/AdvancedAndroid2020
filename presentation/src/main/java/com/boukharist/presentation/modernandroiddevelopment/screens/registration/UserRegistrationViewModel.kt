package com.boukharist.presentation.modernandroiddevelopment.screens.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boukharist.domain.usecase.UserRegistrationFormUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber

class UserRegistrationViewModel(private val formUseCase: UserRegistrationFormUseCase) : ViewModel() {

    val currentViewIndex = MutableLiveData<Int?>()

    init {
        getFormInfoState()
    }

    private fun getFormInfoState() {
        viewModelScope.launch {
            formUseCase.getRegistrationForm()
                .flowOn(Dispatchers.IO)
                .collect {
                    Timber.i("Deeebug : getFormInfoState setCurrentItemIndex ${it.currentPage}")
                    it.currentPage?.let { page -> setCurrentItemIndex(page) }
                }
        }
    }


    fun setCurrentItemIndex(pos: Int) {
        Timber.i("Deeebug : setCurrentItemIndex $pos")
        if (currentViewIndex.value != pos) {
            currentViewIndex.value = pos
        }
    }
}