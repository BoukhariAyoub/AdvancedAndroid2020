package com.boukharist.modernandroiddevelopment.screens.registration

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> UserRegistrationBasicInfoFragment()
            1 -> UserRegistrationSecondaryInfoFragment()
            else -> error("ONLY TWO")
        }
    }
}