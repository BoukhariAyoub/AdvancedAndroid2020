package com.boukharist.screens.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.boukharist.presentation.R
import com.boukharist.presentation.databinding.FormViewBinding
import com.boukharist.screens.MainActivity
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class FormFragment : Fragment() {

    private val scope = this.getKoin().getOrCreateScope(named<MainActivity>().toString(), named<MainActivity>())
    private val viewModel: FormViewModel by scope.viewModel(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FormViewBinding>(inflater, R.layout.form_view, container, false)
            .also {
                it.viewModel = viewModel
                it.lifecycleOwner = viewLifecycleOwner
            }
            .root
    }

}
