package com.onebit.mytraining

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.onebit.mytraining.model.Plan

/**
 * Created by jason on 10/23/17.
 */
class PlanAdapter(private val context: Context, private val list: List<Plan>):
        RecyclerView.Adapter<PlanAdapter.ViewHolder>() {

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
        val plan: Plan = list.get(position)
        holder?.planTitle!!.text = plan.title

    }

    override fun getItemCount(): Int {
        return list.size;
    }
}