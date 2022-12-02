package com.example.discgolfpracticeaid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.discgolfpracticeaid.databinding.ActivityPreviousGameBinding
import com.example.discgolfpracticeaid.fragments.PreviousGameFragment


class PreviousGameActivity: AppCompatActivity() {

    lateinit var binding: ActivityPreviousGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPreviousGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainerView,PreviousGameFragment(),"Previous Games");
        transaction.addToBackStack("Previous Games");
        transaction.commit();
    }

    override fun onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed()
        finish()
        val i = Intent(applicationContext, MainActivity::class.java)
        startActivity(i)
    }

    fun goToHomePage() {
        val HomePageActivity = Intent(this, HomePageActivity::class.java)
        startActivity(HomePageActivity)
        finish()
    }
}