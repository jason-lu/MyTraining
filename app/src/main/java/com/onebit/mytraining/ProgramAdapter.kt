package com.onebit.mytraining

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.onebit.mytraining.model.FragComm
import com.onebit.mytraining.model.Program

/**
 * Created by jason on 10/23/17.
 */
class ProgramAdapter(private val context: Context, private val list: List<Program>,val fragComm: FragComm):
        RecyclerView.Adapter<ProgramAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var programTitle: TextView = itemView.findViewById(R.id.tv_program_title)
        var programDate: TextView = itemView.findViewById(R.id.tv_program_date)
        var programTrainer: TextView = itemView.findViewById(R.id.tv_program_trainer)
        var programTrainee: TextView = itemView.findViewById(R.id.tv_program_trainee)
        var programMainGoal: TextView = itemView.findViewById(R.id.tv_program_maingoal)
        var programProGoal: TextView = itemView.findViewById(R.id.tv_program_progoal)
        var programRestrain: TextView = itemView.findViewById(R.id.tv_program_restrains)
        var date: String = itemView.resources.getString(R.string.date)
        val trainer: String = itemView.resources.getString(R.string.trainer)
        val trainee: String = itemView.resources.getString(R.string.trainee)
        val mainGoal: String = itemView.resources.getString(R.string.main_goal)
        val proGoal: String = itemView.resources.getString(R.string.pro_goal)
        val restrains: String = itemView.resources.getString(R.string.restrains)
        @SuppressLint("SetTextI18n")
        fun bindItems(program: Program, pos: Int, fragComm: FragComm) {
            programTitle.text = program.title
            programDate.text = "$date ${program.date}"
            programDate.text = "$date ${program.date}"
            programTrainer.text = "$trainer ${program.trainer}"
            programTrainee.text = "$trainee ${program.trainee}"
            programMainGoal.text = "$mainGoal ${program.mainGoal}"
            programProGoal.text = "$proGoal ${program.proGoal}"
            programRestrain.text = "$restrains ${program.restrains}"
            itemView.setOnClickListener ({
                v -> fragComm.sendProgram(pos)
            })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_plan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(list[position],position, fragComm)

    }

    override fun getItemCount(): Int {
        return list.size
    }
}