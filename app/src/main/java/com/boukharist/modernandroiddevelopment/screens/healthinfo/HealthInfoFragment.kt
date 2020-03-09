package com.boukharist.modernandroiddevelopment.screens.healthinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.boukharist.modernandroiddevelopment.R
import com.boukharist.modernandroiddevelopment.databinding.HealthInfoViewBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HealthInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : HealthInfoViewBinding = DataBindingUtil.inflate(inflater, R.layout.health_info_view, container, false)
        return binding.apply {
           // this.viewModel = viewModel
        //    this.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
