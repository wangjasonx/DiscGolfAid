package com.example.discgolfpracticeaid
import com.example.discgolfpracticeaid.datamodels.PrevGameModel
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.discgolfpracticeaid.databinding.PreviousGameItemBinding

class PreviousGameAdapter(private val gamesList: List<PrevGameModel>): RecyclerView.Adapter<PreviousGameAdapter.MyViewHolder>() {

    fun getListData(): List<PrevGameModel>{
        return gamesList;
    }

    override fun onBindViewHolder(holder: PreviousGameAdapter.MyViewHolder, position: Int) {

        val game = gamesList[position]
        holder.courseName.text = game.course_name
        holder.date.text = game.date


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviousGameAdapter.MyViewHolder{
       /* val binding = PreviousGameItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return ViewHolder(binding) */

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.previous_game_item,
        parent,false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return gamesList.size
    }

    public class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val courseName : TextView = itemView.findViewById(R.id.tvCourseName)
        val date : TextView = itemView.findViewById(R.id.tvDate)
    }
}