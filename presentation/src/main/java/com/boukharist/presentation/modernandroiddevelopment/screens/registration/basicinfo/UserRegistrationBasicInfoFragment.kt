package com.boukharist.presentation.modernandroiddevelopment.screens.registration.basicinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.boukharist.presentation.R
import com.boukharist.presentation.databinding.UserRegistrationBasicInfoViewBinding
import com.boukharist.presentation.modernandroiddevelopment.screens.registration.UserRegistrationActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class UserRegistrationBasicInfoFragment : Fragment() {

    private val scope: Scope by inject(named<UserRegistrationActivity>())
    private val viewModel: UserRegistrationBasicInfoViewModel by scope.viewModel(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<UserRegistrationBasicInfoViewBinding>(inflater, R.layout.user_registration_basic_info_view, container, false).apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.viewModel = viewModel
        }.root
    }


    override fun onPause() {
        saveInfo()
        super.onPause()
    }

    private fun saveInfo() {
        viewModel.saveInfo()
    }
}
