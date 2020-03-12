package com.boukharist.modernandroiddevelopment

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin

class ModernAppDevelopmentApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initLogger()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@ModernAppDevelopmentApplication)
            logger(AndroidLogger())
            modules(mainModule)
        }
    }

    private fun initLogger() {
      //  Timber.DebugTree().takeIf { BuildConfig.DEBUG }?.let { Timber.plant(it) }
    }
}