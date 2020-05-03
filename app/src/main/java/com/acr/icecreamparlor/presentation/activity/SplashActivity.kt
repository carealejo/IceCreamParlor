package com.acr.icecreamparlor.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.acr.icecreamparlor.R
import com.acr.icecreamparlor.core.platform.BaseActivity


class SplashActivity : BaseActivity() {

    companion object {
        const val SPLASH_TIMER = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable {
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }, SPLASH_TIMER.toLong())
    }
}
