package com.bkstudios.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bkstudios.firebaseauth.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth
        currentUser(view)

    }

    fun signInClicked(view: View){
        var email = binding.emailText.text.toString()
        var password = binding.passwordText.text.toString()

        if(email.equals("") || password.equals("")){
            Toast.makeText(this,"Enter email and password!!!",Toast.LENGTH_LONG).show()
        }else{
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                val intent = Intent(this,FeedAcitivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }

    }


    fun signUpClicked(view: View){

        var email = binding.emailText.text.toString()
        var password = binding.passwordText.text.toString()

        if(email.equals("") || password.equals("")){
            Toast.makeText(this,"Enter email and password!!!",Toast.LENGTH_LONG).show()
        }else{
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                val intent = Intent(this,FeedAcitivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }

    }

    fun currentUser(view: View){
         val currentUser = auth.currentUser
        if(currentUser != null){
            val intent = Intent(this,FeedAcitivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}