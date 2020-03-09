package com.boukharist.modernandroiddevelopment.screens.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.boukharist.modernandroiddevelopment.R
import com.boukharist.modernandroiddevelopment.databinding.UserRegistrationSecondaryInfoViewBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class UserRegistrationSecondaryInfoFragment : Fragment() {
    lateinit var parentViewModel: UserRegistrationViewModel
    lateinit var viewModel: UserRegistrationSecondaryViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        parentViewModel = ViewModelProvider(requireActivity(), UserRegistrationViewModelFactory(requireActivity())).get(UserRegistrationViewModel::class.java)
        viewModel = ViewModelProvider(this).get(UserRegistrationSecondaryViewModel::class.java)

        return DataBindingUtil.inflate<UserRegistrationSecondaryInfoViewBinding>(inflater, R.layout.user_registration_secondary_info_view, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            this.viewModel = viewModel
            this.parentViewModel = parentViewModel
        }.root
    }

    override fun onPause() {
      //  saveInfo()
        super.onPause()
    }

    private fun saveInfo() {
        val goalIndex = viewModel.goalIndex.get() ?: 0
        val activityIndex = viewModel.activityTypeIndex.get() ?: 0
        parentViewModel.saveInfo(goalIndex, activityIndex)
    }
}
