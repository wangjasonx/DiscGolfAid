package com.example.discgolfpracticeaid

import androidx.recyclerview.widget.RecyclerView
import com.example.discgolfpracticeaid.viewmodels.ItemsViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.discgolfpracticeaid.databinding.SetParsItemDesignBinding
import com.example.discgolfpracticeaid.viewmodels.NewGameViewModel

class SetParsAdapter(private val mList: List<ItemsViewModel>, private val gameViewModel: NewGameViewModel) : RecyclerView.Adapter<SetParsAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: SetParsItemDesignBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(holeNumber: String) {
            binding.holeNumber.text = holeNumber

            binding.downArrow.setOnClickListener {
                var shots = binding.Shots.text.toString().toInt()

                if (shots > 0) {
                    shots--
                    binding.Shots.text = shots.toString()
                }
            }

            binding.upArrow.setOnClickListener {
                var shots = binding.Shots.text.toString().toInt()
                shots++

                binding.Shots.text = shots.toString()
            }
        }

        fun saveHoleData() {
            val shots = binding.Shots.text.toString().toInt()

            if (gameViewModel.isParsEmpty() == true) {
                gameViewModel.setPars(shots)
            } else {
                gameViewModel.setShots(shots)
            }
        }
    }

    fun saveParsData() : List<ItemsViewModel> {
        return mList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SetParsItemDesignBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]

        holder.bind(ItemsViewModel.text)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}