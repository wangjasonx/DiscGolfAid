package com.example.discgolfpracticeaid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.discgolfpracticeaid.databinding.ActivityHomePageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // If no one is signed in then return to login page
        if (auth.currentUser == null) {
            goToLogin()
        }

        binding.newGame.setOnClickListener {
            val NewGameActivity = Intent(this, NewGameActivity::class.java)
            startActivity(NewGameActivity)
        }

        binding.signOut.setOnClickListener {
            auth.signOut()
            goToLogin()
        }
    }

    fun goToLogin() {
        val LogInActivity = Intent(this, LogInActivity::class.java)
        startActivity(LogInActivity)
        finish()
    }
}