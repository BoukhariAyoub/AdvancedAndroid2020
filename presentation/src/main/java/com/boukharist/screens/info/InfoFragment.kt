package com.boukharist.screens.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.boukharist.presentation.R
import com.boukharist.presentation.databinding.InfoViewBinding
import com.boukharist.screens.MainActivity
import org.koin.android.ext.android.getKoin
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class InfoFragment : Fragment() {

    private val scope = this.getKoin().getOrCreateScope(named<MainActivity>().toString(), named<MainActivity>())

    private val viewModel: InfoViewModel by scope.viewModel(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<InfoViewBinding>(inflater, R.layout.info_view, container, false)
            .also {
                it.viewModel = viewModel
                it.lifecycleOwner = viewLifecycleOwner
            }
            .root
    }

}
