package com.example.discgolfpracticeaid.fragments

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.discgolfpracticeaid.NewGameActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.discgolfpracticeaid.databinding.ActivityHomePageBinding
import com.example.discgolfpracticeaid.databinding.FragmentNewGameBinding
import com.example.discgolfpracticeaid.viewmodels.NewGameViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import android.app.DatePickerDialog
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.activityViewModels
import com.example.discgolfpracticeaid.databinding.FragmentGameSummaryBinding
import java.util.*

class GameSummaryFragment: Fragment() {
    private lateinit var binding: FragmentGameSummaryBinding
    private val sharedViewModel: NewGameViewModel by activityViewModels()

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(
            com.example.discgolfpracticeaid.R.layout.fragment_game_summary,
            container,
            false
        )

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding = FragmentGameSummaryBinding.inflate(inflater, container, false)


        val name = sharedViewModel.courseName
        val date = sharedViewModel.date
        val score = sharedViewModel.score

        binding.courseName.text = name.toString()
        binding.date.text = date.toString()
        binding.score.text = score.toString()


        binding.goHomeButton.setOnClickListener() {
            val newGameActivity = activity as NewGameActivity
            newGameActivity.goToHomePage()
        }

        return binding.root
    }

}