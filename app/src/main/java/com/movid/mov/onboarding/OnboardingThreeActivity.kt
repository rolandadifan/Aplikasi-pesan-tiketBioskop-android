package com.movid.mov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.movid.mov.R
import com.movid.mov.sign.LoginActivity
import kotlinx.android.synthetic.main.activity_onboarding_three.*

class OnboardingThreeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_three)

        btn_tologin1.setOnClickListener {
            finishAffinity()
            var intent = Intent(this@OnboardingThreeActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
