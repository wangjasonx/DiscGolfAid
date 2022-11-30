package com.example.discgolfpracticeaid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import com.example.discgolfpracticeaid.databinding.ActivityHomePageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import android.view.View
import com.example.discgolfpracticeaid.datamodels.HomePageModel
import com.google.android.gms.common.internal.FallbackServiceBroker
import com.google.firebase.firestore.EventListener
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.BarGraphSeries


class HomePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var series: BarGraphSeries<DataPoint>

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

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        updateGraph()
    }

    fun updateGraph() {
        val user = auth.currentUser?.uid
        db.collection(USER_COLLECTION).document(user.toString())
            .collection(GAMES_MAP).addSnapshotListener { qs: QuerySnapshot?, error: FirebaseFirestoreException? ->
                error?.let {
                    Log.e(error.message, "Error!")
                    return@addSnapshotListener
                }
                qs?.let {
                    series = BarGraphSeries<DataPoint>()
                    var scores: ArrayList<Pair<out Int, out Int>> = ArrayList()
                    for (document in qs.documentChanges) {
                        val game = document.document.toObject<HomePageModel>()
                        val score = game.score
                        var flag = true
                        for (x in scores) {
                            if (x.first == score) {
                                x.second.plus(1)
                                flag = false
                            }
                        }
                        if (flag) {
                            scores.add(Pair(score!!, 1))
                        }
                    }
                    for (x in scores) {
                        series.appendData(DataPoint(x.first.toDouble(), x.second.toDouble()), true, 1000)
                    }
                    binding.graph.addSeries(series)
                }

            }

    }

    fun goToLogin() {
        val LogInActivity = Intent(this, LogInActivity::class.java)
        startActivity(LogInActivity)
        finish()
    }

    companion object {
        private const val ACTIVITY_NAME = "HomePageActivity"
        private const val USER_COLLECTION = "users"
        private const val GAMES_MAP = "games"
    }
}