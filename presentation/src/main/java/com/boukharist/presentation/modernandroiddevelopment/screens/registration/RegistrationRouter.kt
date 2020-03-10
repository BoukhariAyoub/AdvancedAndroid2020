package com.boukharist.presentation.modernandroiddevelopment.screens.registration

import android.content.Context
import android.content.Intent
import com.boukharist.presentation.modernandroiddevelopment.screens.healthinfo.HealthInfoActivity

interface RegistrationRouter {
    fun navigateToHealthInfo()
}

class RegistrationRouterImpl(private val context: Context) : RegistrationRouter {

    override fun navigateToHealthInfo() {
        context.startActivity(Intent(context, HealthInfoActivity::class.java))
    }
}