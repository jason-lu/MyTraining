package com.onebit.mytraining

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.onebit.mytraining.model.Program

/**
 * Created by jason on 10/23/17.
 */
class ProgramAdapter(private val context: Context, private val list: List<Program>):
        RecyclerView.Adapter<ProgramAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var programTitle: TextView
        var programDate: TextView
        var programTrainer: TextView
        var programTrainee: TextView
        var programMainGoal: TextView
        var programProGoal: TextView
        var programRestrain: TextView
        var date: String
        val trainer: String
        val trainee: String
        val mainGoal: String
        val proGoal: String
        val restrains: String

        init {
            programTitle = itemView.findViewById(R.id.tv_program_title)
            programDate = itemView.findViewById(R.id.tv_program_date)
            programTrainer = itemView.findViewById(R.id.tv_program_trainer)
            programTrainee = itemView.findViewById(R.id.tv_program_trainee)
            programMainGoal = itemView.findViewById(R.id.tv_program_maingoal)
            programProGoal = itemView.findViewById(R.id.tv_program_progoal)
            programRestrain = itemView.findViewById(R.id.tv_program_restrains)
            date = itemView.resources.getString(R.string.date)
            trainer = itemView.resources.getString(R.string.trainer)
            trainee = itemView.resources.getString(R.string.trainee)
            mainGoal = itemView.resources.getString(R.string.main_goal)
            proGoal = itemView.resources.getString(R.string.pro_goal)
            restrains = itemView.resources.getString(R.string.restrains)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent?.context)
                .inflate(R.layout.card_plan, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val program: Program = list.get(position)
        holder!!.programTitle.text = program.title
        holder.programDate.text = "${holder.date} ${program.date}"
        holder.programTrainer.text = "${holder.trainer} ${program.trainer}"
        holder.programTrainee.text = "${holder.trainee} ${program.trainee}"
        holder.programMainGoal.text = "${holder.mainGoal} ${program.mainGoal}"
        holder.programProGoal.text = "${holder.proGoal} ${program.proGoal}"
        holder.programRestrain.text = "${holder.restrains} ${program.restrains}"

    }

    override fun getItemCount(): Int {
        return list.size
    }
}