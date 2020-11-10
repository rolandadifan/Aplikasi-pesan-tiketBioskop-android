package com.movid.mov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.movid.mov.R
import com.movid.mov.sign.LoginActivity
import com.movid.mov.utils.Preferences
import kotlinx.android.synthetic.main.activity_onboarding_one.*

class OnboardingOneActivity : AppCompatActivity() {

    lateinit var preference: Preferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_one)

        preference = Preferences(this)
        if (preference.getValues("onboarding").equals("1")){
            finishAffinity()
            var intent = Intent(this@OnboardingOneActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        btn_toob2.setOnClickListener {
            var intent = Intent(this@OnboardingOneActivity, OnboardingTwoActivity::class.java)
            startActivity(intent)
        }

        btn_tologin.setOnClickListener {
            preference.setValues("onboarding", "1")
            finishAffinity()
            var intent = Intent(this@OnboardingOneActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
