package com.example.discgolfpracticeaid

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.discgolfpracticeaid.databinding.ActivityHomePageBinding
import com.example.discgolfpracticeaid.datamodels.HomePageModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.DataPointInterface
import com.jjoe64.graphview.series.PointsGraphSeries
import java.lang.Math.ceil
import java.lang.Math.floor


class HomePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var series1: PointsGraphSeries<DataPoint>
    private lateinit var series2: PointsGraphSeries<DataPoint>

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

        binding.prevGame.setOnClickListener {
            val PreviousGamesActivity = Intent(this, PreviousGameActivity::class.java)
            startActivity(PreviousGamesActivity)
        }

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        updateGraph()
    }

    private fun updateGraph() {
        val user = auth.currentUser?.uid
        db.collection(USER_COLLECTION).document(user.toString())
            .collection(GAMES_MAP).addSnapshotListener { qs: QuerySnapshot?, error: FirebaseFirestoreException? ->
                error?.let {
                    Log.e(error.message, "Error!")
                    return@addSnapshotListener
                }
                qs?.let {
                    val series1: PointsGraphSeries<DataPoint> = PointsGraphSeries<DataPoint>()
                    val series2: PointsGraphSeries<DataPoint> = PointsGraphSeries<DataPoint>()
                    var scores: HashMap<String, Array<Int>> = HashMap()
                    var largest: Int = -1
                    for (document in qs.documentChanges) {
                        val game = document.document.toObject<HomePageModel>()
                        val score = game.shots!!.sum()
                        val totalPar = game.pars!!.sum()
                        val date = game.date
                        val data = arrayOf(score, totalPar)


                        scores.set(game.course_name!!, data)
                    }

                    val sortScore = scores.toSortedMap()
                    var count = 0
                    var nameTrack: HashMap<Int, String> = HashMap()
                    for (x in sortScore.entries) {
                        if (x.value[0] > largest) {
                            largest = x.value[0]
                        }
                        if (x.value[1] > largest) {
                            largest = x.value[1]
                        }
                        nameTrack.set(count,x.key)
                        series1.appendData(DataPoint(count.toDouble(), x.value[0].toDouble()),
                            false, 1000)
                        series2.appendData(DataPoint(count.toDouble(), x.value[1].toDouble()),
                            false, 1000)
                        count+=1
                    }
                    series1.setTitle("Scores")

                    series2.setTitle("Par")
                    series2.setColor(Color.RED)

                    binding.graph.addSeries(series1)
                    binding.graph.addSeries(series2)
                    binding.graph.getGridLabelRenderer().setHorizontalLabelsAngle(90)
                    binding.graph.getGridLabelRenderer()
                        .setLabelFormatter(object : DefaultLabelFormatter() {
                            override fun formatLabel(value: Double, isValueX: Boolean): String {
                                if (isValueX) {
                                    if (ceil(value) == floor(value)) {
                                        return nameTrack[value.toInt()].toString()
                                    }
                                    return ""
                                }
                                return super.formatLabel(value, isValueX)
                            }
                        });
                    binding.graph.getLegendRenderer().setVisible(true);
                    binding.graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.BOTTOM);
                    binding.graph.viewport.isScalable = true
                    binding.graph.viewport.isScrollable = false
                    binding.graph.viewport.setMinY(0.0)
                    binding.graph.viewport.setMaxY(largest + 10.0)
                    binding.graph.viewport.setYAxisBoundsManual(true)
                    binding.graph.gridLabelRenderer.horizontalAxisTitle = "Course Name"


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