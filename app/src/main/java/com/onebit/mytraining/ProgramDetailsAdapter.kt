package com.onebit.mytraining

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.onebit.mytraining.model.Exercise


/**
 * Created by jason on 10/29/17.
 */
class ProgramDetailsAdapter(val mContext: Context, var exercises: HashMap<Int,ArrayList<Exercise>>): BaseAdapter() {
    override fun getCount(): Int {
        return exercises.size
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    @SuppressLint("ViewHolder", "InflateParams", "SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val exercise = exercises[0]
        Log.d("exe",exercises.size.toString())
        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val container = inflater.inflate(R.layout.progame_days, null) as LinearLayout
        val tvDay = container.findViewById<TextView>(R.id.tv_days)
        tvDay.text = "Day ${position+1}"
        for (i in exercise!!.indices) {
            val name = TextView(mContext)
            val order = TextView(mContext)
            val reps = TextView(mContext)
            val sets = TextView(mContext)
            val tempo = TextView(mContext)
            val rest = TextView(mContext)
            val exe = exercise[i]
            order.text = exe.order
            name.text= exe.name
            reps.text = exe.reps.toString()
            sets.text = exe.sets.toString()
            tempo.text = exe.tempo
            rest.text = exe.rest.toString()
            container.addView(order)
            container.addView(name)
            container.addView(reps)
            container.addView(sets)
            container.addView(tempo)
            container.addView(rest)

        }
        return container
    }
}