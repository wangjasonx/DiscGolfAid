package com.example.discgolfpracticeaid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.discgolfpracticeaid.R
import com.example.discgolfpracticeaid.SetParsAdapter
import com.example.discgolfpracticeaid.databinding.FragmentPlayGameBinding
import com.example.discgolfpracticeaid.databinding.FragmentSettingParBinding
import com.example.discgolfpracticeaid.viewmodels.ItemsViewModel
import com.example.discgolfpracticeaid.viewmodels.NewGameViewModel

class PlayGameFragment : Fragment() {

    private lateinit var binding: FragmentPlayGameBinding
    private val sharedViewModel: NewGameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPlayGameBinding.inflate(inflater, container, false)

        val test = sharedViewModel.getPars()

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
        binding.playGameList.adapter = SetParsAdapter(data, sharedViewModel)
    }

    companion object {
    }
}