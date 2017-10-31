package com.onebit.mytraining

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.onebit.mytraining.model.Exercise

/**
 * Created by jason on 10/29/17.
 */
class ProgramDetailsAdapter(val mContext: Context, var exercises: ArrayList<Exercise>): BaseAdapter() {
    override fun getCount(): Int {
        return exercises.size
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val linear = LinearLayout(mContext)
        val text = TextView(linear.context)
        val exercise = exercises[position]
        text.text= """${exercise.order} ${exercise.name} ${exercise.reps}"""
        linear.addView(text)
        linear.orientation=LinearLayout.VERTICAL
        return linear
    }
}