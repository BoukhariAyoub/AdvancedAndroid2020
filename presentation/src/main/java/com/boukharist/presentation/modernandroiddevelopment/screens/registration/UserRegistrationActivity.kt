package com.boukharist.presentation.modernandroiddevelopment.screens.registration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.boukharist.presentation.R
import com.boukharist.presentation.databinding.UserRegistrationActivityBinding
import com.boukharist.presentation.modernandroiddevelopment.screens.registration.secondaryInfo.UserRegistrationSecondaryViewModel
import kotlinx.android.synthetic.main.user_registration_activity.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class UserRegistrationActivity : AppCompatActivity() {

    private val scope: Scope by inject(named<UserRegistrationActivity>())
    private val viewModel: UserRegistrationViewModel by scope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<UserRegistrationActivityBinding>(this, R.layout.user_registration_activity)
        binding.also {
            it.viewModel = viewModel
            it.lifecycleOwner = this@UserRegistrationActivity
        }
        setupViewPager()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.currentViewIndex.observe(this, Observer { position ->
            position?.let {
                navigateToPosition(it)
            }
        })
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

    fun navigateToPosition(pos: Int) {
        viewPager.currentItem = pos
        viewModel.setCurrentItemIndex(pos)
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, navigate to previous step.
            navigateToPosition(viewPager.currentItem - 1)
        }
    }

    override fun onDestroy() {
        scope.close()
        super.onDestroy()
    }

}
