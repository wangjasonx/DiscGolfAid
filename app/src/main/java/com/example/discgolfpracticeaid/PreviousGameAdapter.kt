package com.example.discgolfpracticeaid
import com.example.discgolfpracticeaid.datamodels.PrevGameModel
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.discgolfpracticeaid.databinding.PreviousGameItemBinding

class PreviousGameAdapter(private val gamesList: List<PrevGameModel>,
                          private val onItemClicked: (course_name: String?,
                                                      date: String?, score: Int?, holes: Int?
                          ) -> Unit): RecyclerView.Adapter<PreviousGameAdapter.MyViewHolder>() {

    fun getListData(): List<PrevGameModel>{
        return gamesList;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val game = gamesList[position]
        holder.courseName.text = game.course_name
        holder.date.text = game.date
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.previous_game_item,
        parent,false)

        return MyViewHolder(itemView, onItemClicked)
    }

    override fun getItemCount(): Int {
        return gamesList.size
    }

    class MyViewHolder(itemView:View, private val onItemClicked: (course_name: String?,
                                                                  date: String?,
                                                      score: Int?,
                                                      holes: Int?) -> Unit): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val courseName : TextView = itemView.findViewById(R.id.tvCourseName)
        val date : TextView = itemView.findViewById(R.id.tvDate)

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            onItemClicked("test", "test", 3, 3)
        }

    }
}