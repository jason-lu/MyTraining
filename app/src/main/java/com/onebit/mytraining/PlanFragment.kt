package com.onebit.mytraining

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.onebit.mytraining.model.Plan
import kotlinx.android.synthetic.main.fragment_plan.*

/**
 * Created by jason on 20/10/17.
 */
class PlanFragment : Fragment() {
    private lateinit var linearLayoutManager: LinearLayoutManager

    companion object {
        fun newInstance(): PlanFragment {
            return PlanFragment()
        }
        val tag = "PlanFragment"
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val resources = context!!.resources


    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_plan, container, false)
        val list = ArrayList<Plan>()
        initPlan(list)
        val planCardView = view?.findViewById<RecyclerView>(R.id.plan_contianer)
        linearLayoutManager = LinearLayoutManager(context)
        planCardView!!.layoutManager = linearLayoutManager
        planCardView!!.adapter= PlanAdapter(context,list)



        return view
    }

    private fun initPlan(list: ArrayList<Plan>) {
        for (i in 1 .. 10 step 1) {
            list.add(Plan("plan "+ i))
        }
    }
}