package com.acr.icecreamparlor.core.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.acr.icecreamparlor.AndroidApplication
import com.acr.icecreamparlor.core.api.ApplicationComponent

/*
* Base Activity to help general behaviors on the activities
*/
abstract class BaseActivity: AppCompatActivity() {
    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as AndroidApplication).appComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
    }
}