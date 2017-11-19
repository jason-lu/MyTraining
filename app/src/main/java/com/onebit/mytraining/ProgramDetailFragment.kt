package com.onebit.mytraining

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.onebit.mytraining.model.Exercise
import com.onebit.mytraining.util.FragComm

/**
 * Created by jason on 10/25/17.
 */
class ProgramDetailFragment: Fragment() {
    private lateinit var fragComm: FragComm
    private val proViews = HashMap<Int, ArrayList<EditText>>()
    private lateinit var days: HashMap<Int, ArrayList<Exercise>>

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
        days = fragComm.getProgram()[pos].exercises

        val dayContainer = view?.findViewById<LinearLayout>(R.id.day_container)
        val cellInflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        for ((key,value) in days) {
            val day = TextView(context)
            day.text="Day ${key+1}"
            dayContainer?.addView(day)
            val exes = value
            val daliyExe = ArrayList<EditText>()
            for (i in exes.indices) {
                val exe = exes[i]
                val exeCell = cellInflater.inflate(R.layout.exe_item,null,false)
                val order = exeCell.findViewById<EditText>(R.id.et_order)
                val exeName = exeCell.findViewById<EditText>(R.id.et_exe)
                val reps = exeCell.findViewById<EditText>(R.id.et_reps)
                val sets = exeCell.findViewById<EditText>(R.id.et_sets)
                val tempo = exeCell.findViewById<EditText>(R.id.et_tempo)
                val rest = exeCell.findViewById<EditText>(R.id.et_rest)

                order.setText(exe.order)
                exeName.setText(exe.name)
                reps.setText(exe.reps.toString())
                sets.setText(exe.sets.toString())
                tempo.setText(exe.tempo)
                rest.setText(exe.rest.toString())
                daliyExe.add(order)
                daliyExe.add(exeName)
                daliyExe.add(reps)
                daliyExe.add(sets)
                daliyExe.add(tempo)
                daliyExe.add(rest)

                proViews.put(key,daliyExe)

                dayContainer?.addView(exeCell)

            }
        }

        return view
    }

    override fun onPause() {
        super.onPause()
        val newDays: HashMap<Int, ArrayList<Exercise>> = HashMap()
        for( (day, exes) in proViews ) {
            val newDay: ArrayList<Exercise> = ArrayList()
            val siz = exes.size/6
            (0 until exes.size step 6).mapTo(newDay) {
                Exercise(
                        exes[it].text.toString(),
                        exes[it +1].text.toString(),
                        exes[2+ it].text.toString().toInt(),
                        exes[3+ it].text.toString().toInt(),
                        exes[4+ it].text.toString(),
                        exes[5+ it].text.toString().toInt()
                )
            }
            newDays.put(day,newDay)
        }

        fragComm.saveProgram(pos,newDays)
    }

}