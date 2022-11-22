package com.example.discgolfpracticeaid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.discgolfpracticeaid.databinding.ActivityNewGameBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private lateinit var binding: ActivityNewGameBinding
private lateinit var auth: FirebaseAuth
private lateinit var db: FirebaseFirestore

class NewGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)

    }
}