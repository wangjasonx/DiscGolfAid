package com.example.discgolfpracticeaid.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.discgolfpracticeaid.PreviousGameAdapter
import com.example.discgolfpracticeaid.R
import com.example.discgolfpracticeaid.datamodels.PrevGameModel
import com.example.discgolfpracticeaid.viewmodels.NewGameViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject


class PreviousGameFragment : Fragment(com.example.discgolfpracticeaid.R.layout.fragment_previous_game) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var gamesList: ArrayList<PrevGameModel>
    private lateinit var myAdapter: PreviousGameAdapter
    private val sharedViewModel: NewGameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(com.example.discgolfpracticeaid.R.layout.fragment_previous_game, container, false)
        recyclerView= view.findViewById<View>(com.example.discgolfpracticeaid.R.id.recylerView_PG) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        gamesList = arrayListOf()

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        myAdapter = PreviousGameAdapter(gamesList) {
            course_name, date, score, holes ->  onListItemClick(course_name, date, score, holes)
        }

        recyclerView.adapter = myAdapter

        updateGameList()
        return view
    }

    private fun onListItemClick(course_name: String?, date: String?, score: Int?, holes: Int?) {
        sharedViewModel.setCourseName(course_name!!)
        sharedViewModel.setDate(date!!)
        sharedViewModel.setScore(score!!)
        sharedViewModel.setNumberOfHoles(holes!!)
        parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, GameSummaryFragment()).commit()
    }

    private fun updateGameList() {
        val user = auth.currentUser?.uid
        db.collection(USER_COLLECTION).document(user.toString())
            .collection(GAMES_MAP).addSnapshotListener { qs: QuerySnapshot?, error: FirebaseFirestoreException? ->
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


    companion object {
        private const val ACTIVITY_NAME = "PreviousGameActivity"
        private const val USER_COLLECTION = "users"
        private const val GAMES_MAP = "games"
    }
}