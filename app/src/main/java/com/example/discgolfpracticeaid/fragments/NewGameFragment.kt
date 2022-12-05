package com.example.discgolfpracticeaid.fragments

import android.app.DatePickerDialog
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
import java.util.*

class NewGameFragment : Fragment() {
    private lateinit var binding: FragmentNewGameBinding
    private val sharedViewModel: NewGameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewGameBinding.inflate(inflater, container, false)

        binding.Date.setOnClickListener{
            val c = Calendar.getInstance()

            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                inflater.context,
                { view, year, monthOfYear, dayOfMonth ->
                    var monthYear = (monthOfYear + 1).toString()
                    var dayMonth = dayOfMonth.toString()
                    if (monthYear.length < 2) {
                        monthYear = "0" + monthYear
                    }
                    if (dayMonth.length < 2) {
                        dayMonth = "0" + dayMonth
                    }
                    val dat = (monthYear + "-" + dayMonth + "-" + year)
                    binding.Date.setText(dat)
                },
                year,
                month,
                day
            )

            datePickerDialog.show()
        }

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

            sharedViewModel.setNumberOfHoles(holesNumber)
            sharedViewModel.setCourseName(courseName)
            sharedViewModel.setDate(Date)

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