package com.example.discgolfpracticeaid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.discgolfpracticeaid.databinding.LogInActivityBinding

private lateinit var binding: LogInActivityBinding

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in_activity)

        binding = LogInActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerAnAccountLink.setOnClickListener {
            goToSignUp()
        }
    }

    fun goToSignUp() {
        val SignUpActivity = Intent(this, SignUpActivity::class.java)
        startActivity(SignUpActivity)
        finish()
    }
}