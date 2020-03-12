package com.boukharist.screens.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.boukharist.presentation.R
import kotlinx.android.synthetic.main.form_view.*

class FormFragment : Fragment() {

    private lateinit var viewModel: FormViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.form_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FormViewModel::class.java)
        initObservers()
        setListeners()
    }

    override fun onDestroyView() {
        val firstName = firstNameText.editText!!.text.toString()
        val lastName = lastNameText.editText!!.text.toString()
        val birthDate = birthDateText.editText!!.text.toString()
        viewModel.onFormTextChanged(firstName, lastName, birthDate)
        super.onDestroyView()
    }

    private fun initObservers() {
        viewModel.formLiveData.observe(viewLifecycleOwner, Observer {
            firstNameText.editText!!.setText(it.firstName)
            lastNameText.editText!!.setText(it.lastName)
            birthDateText.editText!!.setText(it.birthDate)
        })
    }

    private fun setListeners() {
        validateButton.setOnClickListener {
            val firstName = firstNameText.editText!!.text.toString()
            val lastName = lastNameText.editText!!.text.toString()
            val birthDate = birthDateText.editText!!.text.toString()
            viewModel.onValidateClicked(firstName, lastName, birthDate)
        }
    }
}
