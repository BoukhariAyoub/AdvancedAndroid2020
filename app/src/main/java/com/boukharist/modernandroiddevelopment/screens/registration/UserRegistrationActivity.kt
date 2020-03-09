package com.boukharist.modernandroiddevelopment.screens.registration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.boukharist.modernandroiddevelopment.R
import com.boukharist.modernandroiddevelopment.databinding.UserRegistrationActivityBinding
import com.boukharist.modernandroiddevelopment.databinding.UserRegistrationBasicInfoViewBinding
import kotlinx.android.synthetic.main.user_registration_activity.*

class UserRegistrationActivity : AppCompatActivity() {

    lateinit var viewModel: UserRegistrationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, UserRegistrationViewModelFactory(this)).get(UserRegistrationViewModel::class.java)
        val binding = DataBindingUtil.setContentView<UserRegistrationActivityBinding>(this, R.layout.user_registration_activity)
        binding.apply {
            lifecycleOwner = this@UserRegistrationActivity
            this.viewModel = viewModel
        }
        setupViewPager()
        setupObservers()
    }

    private fun setupObservers() {

        viewModel.currentViewIndex.observe(this, Observer { navigateToPosition(it) })
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 2
        viewPager.isUserInputEnabled = false
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                viewModel.setCurrentItemIndex(position)
            }
        })
    }

    private fun navigateToPosition(pos: Int) {
        viewPager.currentItem = pos
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

}
