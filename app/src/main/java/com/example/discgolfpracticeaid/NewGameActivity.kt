package com.example.discgolfpracticeaid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.discgolfpracticeaid.databinding.ActivityNewGameBinding
import com.example.discgolfpracticeaid.viewmodels.NewGameViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class NewGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewGameBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private val sharedViewModel: NewGameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

    }

    fun addData() {
        sharedViewModel.buildMap()
        val newGameMap = sharedViewModel.newGameMap.value

        val user = auth.currentUser?.uid

        if (newGameMap != null) {
            db.collection(USER_COLLECTION)
                .document(user.toString())
                .collection(GAMES_MAP)
                .document()
                .set(newGameMap)
                .addOnSuccessListener {
                    Toast.makeText(this@NewGameActivity, "Successfully logged game!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this@NewGameActivity, "Error saving game info!", Toast.LENGTH_SHORT).show()
                    Log.e(ACTIVITY_NAME, "Failure saving game map data : Err : " + it.message)
                }
        } else {
            Toast.makeText(this@NewGameActivity, "Something went wrong when adding data, please try again...", Toast.LENGTH_SHORT).show()
        }
    }

    fun goToHomePage() {
        val HomePageActivity = Intent(this, HomePageActivity::class.java)
        startActivity(HomePageActivity)
        finish()
    }

    companion object {
        private const val ACTIVITY_NAME = "NewGameActivity"
        private const val USER_COLLECTION = "users"
        private const val GAMES_MAP = "games"
    }
}