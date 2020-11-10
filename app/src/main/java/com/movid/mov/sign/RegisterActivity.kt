package com.movid.mov.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.Toast
import com.google.firebase.database.*
import com.movid.mov.R
import com.movid.mov.model.User
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    lateinit var sUsername:String
    lateinit var sPassword:String
    lateinit var sNama:String
    lateinit var sEmail:String

    lateinit var mFirebaseDatabaseReference: DatabaseReference
    lateinit var mFirebaseInstances: FirebaseDatabase
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mFirebaseInstances = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mFirebaseDatabaseReference = mFirebaseInstances.getReference("User")

        btn_lanjutkan.setOnClickListener {
            sUsername = et_username.text.toString()
            sPassword = et_password.text.toString()
            sNama = et_nama.text.toString()
            sEmail = et_email.text.toString()

            if (sUsername.equals("")){
                et_username.error = "Silahkan isi username Anda"
                et_username.requestFocus()
            }else if (sPassword.equals("")){
                et_password.error = "Silahkan isi password Anda"
                et_password.requestFocus()
            }else if (sNama.equals("")){
                et_nama.error = "Silahkan isi nama Anda"
                et_nama.requestFocus()
            }else if (sEmail.equals("")){
                et_email.error = "Silahkan isi nama Anda"
                et_email.requestFocus()
            }else{
                saveUsername(sUsername,sPassword,sNama,sEmail)
            }
        }


    }

    private fun saveUsername(sUsername: String, sPassword: String, sNama: String, sEmail: String) {
        var user = User()
        user.email = sEmail
        user.username = sUsername
        user.password = sPassword
        user.nama = sNama

        if (sUsername != null){
            checingUsername(sUsername, user)
        }
    }

    private fun checingUsername(sUsername: String, data: User) {
        mFirebaseDatabaseReference.child(sUsername).addValueEventListener(object : ValueEventListener{
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@RegisterActivity, "" +databaseError.message,
                    Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user == null){
                    mFirebaseDatabaseReference.child(sUsername).setValue(data)

                    var goRegisterPhoto = Intent(this@RegisterActivity, RegiterPhotoActivity::class.java).putExtra("nama", data.nama)
                    startActivity(goRegisterPhoto)
                }else{
                    Toast.makeText(this@RegisterActivity, "user sudah digunakan",
                        Toast.LENGTH_LONG).show()
                }
            }

        })
    }
}
