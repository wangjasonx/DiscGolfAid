package com.example.discgolfpracticeaid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.discgolfpracticeaid.R
import com.example.discgolfpracticeaid.databinding.FragmentNewGameBinding
import com.example.discgolfpracticeaid.viewmodels.NewGameViewModel
import androidx.fragment.app.activityViewModels


private lateinit var binding: FragmentNewGameBinding

class NewGameFragment : Fragment() {

    private val sharedViewModel: NewGameViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewGameBinding.inflate(inflater, container, false)

        binding.setParsButton.setOnClickListener {
            val courseName = binding.courseName.text.toString().trim()
            val Date = binding.Date.text.toString().trim()
            val numberOfHoles = binding.numberOfHoles.text.toString().trim()

            if (courseName.isEmpty()) {
                binding.courseName.error = "Course Name cannot be empty!"
                return@setOnClickListener
            }

            if (Date.isEmpty()) {
                binding.Date.error = "Date cannot be empty!"
                return@setOnClickListener
            }

            if (numberOfHoles.isEmpty()) {
                binding.numberOfHoles.error = "# of holes cannot be empty!"
                return@setOnClickListener
            }

            val holesNumber = numberOfHoles.toInt()

            sharedViewModel.setNewGameMap(courseName, Date)
            sharedViewModel.setNumberOfHoles(holesNumber)

            goToSetParsScreen()
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    fun goToSetParsScreen() {
        findNavController().navigate(R.id.action_newGameFragment_to_settingParFragment)
    }

    companion object {
    }
}