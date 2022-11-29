package com.example.discgolfpracticeaid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.discgolfpracticeaid.datamodels.PrevGameModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject

class PreviousGamesActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView


    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var gamesList: ArrayList<PrevGameModel>
    private lateinit var myAdapter:PreviousGameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previous_game_page2)

        recyclerView = findViewById(R.id.recylerView_PG)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        gamesList = arrayListOf()

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        myAdapter = PreviousGameAdapter(gamesList)

        recyclerView.adapter = myAdapter

        updateGameList()
    }

    private fun updateGameList() {
        val user = auth.currentUser?.uid
        db.collection(USER_COLLECTION).document(user.toString())
            .collection(GAMES_MAP).addSnapshotListener { qs:QuerySnapshot?, error:FirebaseFirestoreException? ->
                error?.let {
                    Log.e(error.message, "Error!")
                    return@addSnapshotListener
                }
                qs?.let {
                    for (document in qs.documentChanges) {
                        val game = document.document.toObject<PrevGameModel>()
                        gamesList.add(game)
                    }

                    myAdapter.notifyDataSetChanged()
                }

            }

    }

    fun goToHomePage() {
        val HomePageActivity = Intent(this, HomePageActivity::class.java)
        startActivity(HomePageActivity)
        finish()
    }

    companion object {
        private const val ACTIVITY_NAME = "PreviousGameActivity"
        private const val USER_COLLECTION = "users"
        private const val GAMES_MAP = "games"
    }
}