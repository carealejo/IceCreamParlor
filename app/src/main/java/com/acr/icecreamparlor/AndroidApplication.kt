package com.acr.icecreamparlor

import android.app.Application
import com.acr.icecreamparlor.core.api.ApplicationComponent
import com.acr.icecreamparlor.core.api.DaggerApplicationComponent
import com.acr.icecreamparlor.core.di.ApplicationModule
//import com.acr.baseapp.domain.model.User

class AndroidApplication : Application() {

    //var user: User? = null

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }

    private fun injectMembers() = appComponent.inject(this)
}
