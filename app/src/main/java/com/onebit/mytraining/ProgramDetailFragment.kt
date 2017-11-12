package com.onebit.mytraining

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.onebit.mytraining.util.FragComm

/**
 * Created by jason on 10/25/17.
 */
class ProgramDetailFragment: Fragment() {
    private lateinit var fragComm: FragComm
    val views = ArrayList<EditText>()

    companion object {
        private var pos: Int = -1
        fun newInstance(p: Int): ProgramDetailFragment {
            pos = p
            return ProgramDetailFragment()
        }
        val tag = "ProgramDetailFragment"
    }


    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_program_detail, container, false)
        fragComm = activity as FragComm
        val days = fragComm.getProgram()[pos].exercises

        val dayContainer = view?.findViewById<LinearLayout>(R.id.day_container)
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        for ((key,value) in days) {
            val day = TextView(context)
            day.text="Day ${key+1}"
            dayContainer?.addView(day)
            val exes = value
            for (i in exes.indices) {
                val exeCell = inflater.inflate(R.layout.exe_item,null,false)
                dayContainer?.addView(exeCell)

            }
        }

        return view
    }

    override fun onPause() {
        val str = StringBuilder()
        for(i in views.indices) {
            str.append(views[i].text.toString())
        }
        Toast.makeText(context,str.toString(),Toast.LENGTH_LONG).show()
        super.onPause()
    }
}