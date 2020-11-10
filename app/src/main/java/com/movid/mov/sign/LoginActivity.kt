package com.movid.mov.sign

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.movid.mov.home.HomeActivity
import com.movid.mov.R
import com.movid.mov.model.User
import com.movid.mov.utils.Preferences
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    lateinit var iUsername:String
    lateinit var iPassword:String
    lateinit var mDatabase: DatabaseReference
    lateinit var preference: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preference = Preferences(this)

        preference.setValues("onboarding", "1")

//        hapus // ketika ingin auto login pada saat sudah login
//        if (preference.getValues("status").equals("1")){
//            finishAffinity()
//            var goHome = Intent(this@LoginActivity, HomeActivity::class.java)
//            startActivity(goHome)
//        }
        btn_login.setOnClickListener {
            iUsername = et_username.text.toString()
            iPassword = et_password.text.toString()

            if (iUsername.equals("")){
                et_username.error = "silahkan tulis username anda"
                et_username.requestFocus()
            }else if (iPassword.equals("")){
                et_password.error = "silahkan tulis passwprd anda"
                et_password.requestFocus()
            }else{
                pushLogin(iUsername, iPassword)
            }
        }
        btn_register.setOnClickListener {
            var intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener{
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@LoginActivity, databaseError.message,
                    Toast.LENGTH_LONG).show()
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    Toast.makeText(
                        this@LoginActivity, "user tidak ditemukan",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    if (user.password.equals(iPassword)) {

                        preference.setValues("nama", user.nama.toString())
                        preference.setValues("user", user.username.toString())
                        preference.setValues("url", user.url.toString())
                        preference.setValues("email", user.email.toString())
                        preference.setValues("saldo", user.saldo.toString())
                        preference.setValues("status", "1")

                        var intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@LoginActivity, "Password Anda Salah",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }
            }
        })
    }

}
