package com.example.discgolfpracticeaid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.discgolfpracticeaid.databinding.ActivityHomePageBinding

private lateinit var binding: ActivityHomePageBinding

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.newGame.setOnClickListener {
            val NewGameActivity = Intent(this, NewGameActivity::class.java)
            startActivity(NewGameActivity)
            finish()
        }
    }
}