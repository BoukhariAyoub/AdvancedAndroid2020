package com.boukharist.presentation.modernandroiddevelopment.screens.healthinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.boukharist.presentation.R
import com.boukharist.presentation.databinding.HealthInfoViewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HealthInfoFragment : Fragment() {

    private val viewModel: HealthInfoViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: HealthInfoViewBinding = DataBindingUtil.inflate(inflater, R.layout.health_info_view, container, false)
        return binding.also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observeUser()
    }
}
