package com.boukharist

import android.app.Application
import com.boukharist.modernandroiddevelopment.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import timber.log.Timber

class AndroidModernDevelopmentApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initLogger()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@AndroidModernDevelopmentApplication)
            logger(AndroidLogger())
            modules(applicationModules)
        }
    }

    private fun initLogger() {
        Timber.DebugTree().takeIf { BuildConfig.DEBUG }?.let { Timber.plant(it) }
    }
}