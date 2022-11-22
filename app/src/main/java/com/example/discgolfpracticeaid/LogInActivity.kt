package com.example.discgolfpracticeaid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.discgolfpracticeaid.databinding.LogInActivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private lateinit var binding: LogInActivityBinding
private lateinit var auth: FirebaseAuth
private lateinit var db: FirebaseFirestore

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in_activity)

        binding = LogInActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        if (auth.currentUser != null) {
            goToHomePage()
        }

        binding.Login.setOnClickListener {
            val Email = binding.Email.text.toString().trim()
            val Password = binding.Password.text.toString().trim()

            if (Email.isEmpty()) {
                binding.Email.error = "Email cannot be empty!"
                return@setOnClickListener
            }

            if (Password.isEmpty()) {
                binding.Password.error = "Password cannot be empty!"
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(ACTIVITY_NAME, "signInWithEmail:success")
                        val user = auth.currentUser
                        goToHomePage()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(ACTIVITY_NAME, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.registerAnAccountLink.setOnClickListener {
            goToSignUp()
        }
    }

    fun goToSignUp() {
        val SignUpActivity = Intent(this, SignUpActivity::class.java)
        startActivity(SignUpActivity)
        finish()
    }

    fun goToHomePage() {
        val HomePageActivity = Intent(this, HomePageActivity::class.java)
        startActivity(HomePageActivity)
        finish()
    }

    companion object {
        private const val ACTIVITY_NAME = "LoginActivity"
    }
}