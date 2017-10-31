package com.onebit.mytraining

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.onebit.mytraining.model.FragComm
import kotlinx.android.synthetic.main.card_plan.*

/**
 * Created by jason on 20/10/17.
 */
class ProgramFragment : Fragment() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var fragComm: FragComm
    private lateinit var planCardView: RecyclerView
    private lateinit var emptyPlan: TextView


    companion object {
        fun newInstance(): ProgramFragment {
            return ProgramFragment()
        }
        val tag = "ProgramFragment"
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_plan, container, false)
        fragComm = activity as FragComm
        emptyPlan = view!!.findViewById(R.id.tv_empty_program)
        planCardView = view.findViewById(R.id.plan_contianer)
        linearLayoutManager = LinearLayoutManager(context)
        planCardView.layoutManager = linearLayoutManager
        planCardView.adapter= ProgramAdapter(context,fragComm.getProgram(), fragComm)
        if(fragComm.getProgram().size ==0){
            planCardView.visibility= View.GONE
            emptyPlan.visibility=View.VISIBLE
        } else {
            planCardView.visibility= View.VISIBLE
            emptyPlan.visibility=View.GONE
        }
        return view
    }
}