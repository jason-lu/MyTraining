package com.onebit.mytraining

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.onebit.mytraining.model.Day
import com.onebit.mytraining.model.Program
import java.util.*

/**
 * Created by jason on 20/10/17.
 */
class ProgramFragment : Fragment() {
    private lateinit var linearLayoutManager: LinearLayoutManager

    companion object {
        fun newInstance(): ProgramFragment {
            return ProgramFragment()
        }
        val tag = "ProgramFragment"
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val resources = context!!.resources


    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_plan, container, false)
        val list = ArrayList<Program>()
        initPlan(list)
        val planCardView = view?.findViewById<RecyclerView>(R.id.plan_contianer)
        linearLayoutManager = LinearLayoutManager(context)
        planCardView!!.layoutManager = linearLayoutManager
        planCardView.adapter= ProgramAdapter(context,list)



        return view
    }

    private fun initPlan(list: ArrayList<Program>) {
        val days = ArrayList<Day>()
        val program = Program(title = "My Training plan",date = Date(),trainer = "Dan",trainee = "Kuan",
                mainGoal = "Lose weight", proGoal = "Correct movement patterns.",
                restrains = "Overall Tightness." , days = days)
        for(i in 1 .. 5 step 1) {
            list.add(program)
        }
    }
}