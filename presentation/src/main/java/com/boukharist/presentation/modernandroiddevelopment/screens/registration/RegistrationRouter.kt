package com.boukharist.presentation.modernandroiddevelopment.screens.registration

import android.content.Context
import android.content.Intent
import com.boukharist.presentation.modernandroiddevelopment.screens.healthinfo.HealthInfoActivity
import kotlinx.android.synthetic.main.user_registration_activity.*

interface RegistrationRouter {
    fun navigateToHealthInfo()
}

class RegistrationRouterImpl(private val context: Context) : RegistrationRouter {

    override fun navigateToHealthInfo() {
        context.startActivity(Intent(context, HealthInfoActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }
}