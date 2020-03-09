package com.boukharist.modernandroiddevelopment.screens.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.lifecycle.ViewModelProvider
import com.boukharist.modernandroiddevelopment.R
import com.boukharist.modernandroiddevelopment.databinding.UserRegistrationBasicInfoViewBinding
import com.boukharist.modernandroiddevelopment.databinding.UserRegistrationSecondaryInfoViewBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class UserRegistrationBasicInfoFragment : Fragment() {

    private lateinit var parentViewModel: UserRegistrationViewModel
    private lateinit var viewModel: UserRegistrationBasicInfoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        parentViewModel = ViewModelProvider(requireActivity(), UserRegistrationViewModelFactory(requireActivity())).get(UserRegistrationViewModel::class.java)
        viewModel = ViewModelProvider(this).get(UserRegistrationBasicInfoViewModel::class.java)

        return DataBindingUtil.inflate<UserRegistrationBasicInfoViewBinding>(
            inflater,
            R.layout.user_registration_basic_info_view,
            container,
            false
        ).apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.viewModel = viewModel
            this.parentViewModel = parentViewModel
        }.root
    }


    override fun onPause() {
        //saveInfo()
        super.onPause()
    }

    private fun saveInfo() {
        val isMale = viewModel.isMale.get() ?: false
        val height = viewModel.height.get() ?: ""
        val weight = viewModel.weight.get() ?: ""
        val birthDate = viewModel.birthDate.get() ?: ""
        parentViewModel.saveInfo(isMale, birthDate, height, weight)
    }
}
