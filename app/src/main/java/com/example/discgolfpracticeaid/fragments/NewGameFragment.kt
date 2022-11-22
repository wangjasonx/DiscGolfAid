package com.example.discgolfpracticeaid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.discgolfpracticeaid.R
import com.example.discgolfpracticeaid.databinding.FragmentNewGameBinding

private lateinit var binding: FragmentNewGameBinding

class NewGameFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewGameBinding.inflate(inflater, container, false)

        binding.setParsButton.setOnClickListener {
            val setParFragment = SettingParFragment()
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, setParFragment, "setParFragment")

        }

        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
    }
}