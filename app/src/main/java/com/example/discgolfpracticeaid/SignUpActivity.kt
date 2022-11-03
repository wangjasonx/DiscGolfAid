package com.example.discgolfpracticeaid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.discgolfpracticeaid.databinding.LogInActivityBinding
import com.example.discgolfpracticeaid.databinding.SignUpActivityBinding

private lateinit var binding: SignUpActivityBinding

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_activity)

        binding = SignUpActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.haveAnAccountLink.setOnClickListener {
            val LogInActivity = Intent(this, LogInActivity::class.java)
            startActivity(LogInActivity)
            finish()
        }
    }
}