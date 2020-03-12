package com.boukharist.presentation.modernandroiddevelopment.screens.registration.secondaryInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.boukharist.presentation.R
import com.boukharist.presentation.databinding.UserRegistrationSecondaryInfoViewBinding
import com.boukharist.presentation.modernandroiddevelopment.screens.registration.UserRegistrationActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class UserRegistrationSecondaryInfoFragment : Fragment() {

    private val scope: Scope by inject(named<UserRegistrationActivity>())
    private val viewModel: UserRegistrationSecondaryViewModel by scope.viewModel(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<UserRegistrationSecondaryInfoViewBinding>(inflater, R.layout.user_registration_secondary_info_view, container, false)
            .also {
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = viewModel
        }.root
    }

    override fun onPause() {
       // viewModel.saveInfo()
        super.onPause()
    }
}
