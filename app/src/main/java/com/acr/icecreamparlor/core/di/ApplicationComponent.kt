package com.acr.icecreamparlor.core.api

import com.acr.icecreamparlor.AndroidApplication
import com.acr.icecreamparlor.core.di.ApplicationModule
import com.acr.icecreamparlor.presentation.activity.WelcomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [ApplicationModule::class]
)

interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(welcomeActivity: WelcomeActivity)
}
