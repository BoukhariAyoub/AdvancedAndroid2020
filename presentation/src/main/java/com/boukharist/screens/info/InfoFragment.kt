package com.boukharist.screens.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.boukharist.mvarchi.info.InfoViewModel
import com.boukharist.mvarchi.info.InfoViewModelFactory
import com.boukharist.presentation.R
import com.boukharist.presentation.databinding.InfoViewBinding

class InfoFragment : Fragment() {

    private lateinit var viewModel: InfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, InfoViewModelFactory()).get(InfoViewModel::class.java)
        return DataBindingUtil.inflate<InfoViewBinding>(inflater, R.layout.info_view, container, false)
            .also {
                it.viewModel = viewModel
                it.lifecycleOwner = viewLifecycleOwner
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchData()
    }
}
