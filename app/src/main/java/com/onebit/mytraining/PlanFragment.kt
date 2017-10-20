package com.onebit.mytraining

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by jason on 20/10/17.
 */
class PlanFragment : Fragment() {

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

        return view
    }
}