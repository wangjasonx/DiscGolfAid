package com.example.discgolfpracticeaid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.discgolfpracticeaid.NewGameActivity
import com.example.discgolfpracticeaid.R
import com.example.discgolfpracticeaid.SetParsAdapter
import com.example.discgolfpracticeaid.databinding.FragmentPlayGameBinding
import com.example.discgolfpracticeaid.viewmodels.ItemsViewModel
import com.example.discgolfpracticeaid.viewmodels.NewGameViewModel

class PlayGameFragment : Fragment() {

    private lateinit var binding: FragmentPlayGameBinding
    private val sharedViewModel: NewGameViewModel by activityViewModels()
    private lateinit var adapter: SetParsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPlayGameBinding.inflate(inflater, container, false)

        binding.finishPlayingButton.setOnClickListener {
            val newGameActivity = activity as NewGameActivity
            val scoreData = adapter.getListData()

            for (item in scoreData) {
                sharedViewModel.shots.value?.add(item.shots)
            }

            sharedViewModel.setScore(calculateScore())

            newGameActivity.addData()
            newGameActivity.goToHomePage()
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = ArrayList<ItemsViewModel>()

        for (i in 1..sharedViewModel.numberOfHoles.value!!) {
            data.add(ItemsViewModel("Hole#$i", 3))
        }

        binding.playGameList.layoutManager = LinearLayoutManager(activity)

        adapter = SetParsAdapter(data, sharedViewModel)
        binding.playGameList.adapter = adapter
    }

    fun calculateScore() : Int {
        val parList = sharedViewModel.pars.value?.iterator()
        val shotsList = sharedViewModel.shots.value?.iterator()

        var score = 0

        while (parList!!.hasNext() && shotsList!!.hasNext()) {
            val parScore = parList.next()
            val shotsScore = shotsList.next()

            score += (shotsScore - parScore)
        }

        return score
    }
}