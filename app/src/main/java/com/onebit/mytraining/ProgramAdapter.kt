package com.onebit.mytraining

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
        var planTitle: TextView

        init {
            planTitle = itemView.findViewById(R.id.tv_plan_title);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent?.context)
                .inflate(R.layout.card_plan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val program: Program = list.get(position)
        holder?.planTitle!!.text = program.title

    }

    override fun getItemCount(): Int {
        return list.size;
    }
}