package com.example.discgolfpracticeaid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.discgolfpracticeaid.R
import com.example.discgolfpracticeaid.SetParsAdapter
import com.example.discgolfpracticeaid.databinding.FragmentSettingParBinding
import com.example.discgolfpracticeaid.viewmodels.ItemsViewModel
import com.example.discgolfpracticeaid.viewmodels.NewGameViewModel

class SettingParFragment : Fragment() {
    private lateinit var binding: FragmentSettingParBinding
    private val sharedViewModel: NewGameViewModel by activityViewModels()
    private lateinit var adapter: SetParsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingParBinding.inflate(inflater, container, false)

        binding.startGameButton.setOnClickListener {
            val parData = adapter.getListData()

            for (item in parData) {
                sharedViewModel.pars.value?.add(item.shots)
            }
            goToPlayGameScreen()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = ArrayList<ItemsViewModel>()

        for (i in 1..sharedViewModel.numberOfHoles.value!!) {
            data.add(ItemsViewModel("Hole#$i", 3))
        }

        binding.listView.layoutManager = LinearLayoutManager(activity)

        adapter = SetParsAdapter(data, sharedViewModel)
        binding.listView.adapter = adapter
    }

    fun goToPlayGameScreen() {
        findNavController().navigate(R.id.action_settingParFragment_to_playGameFragment)
    }

}