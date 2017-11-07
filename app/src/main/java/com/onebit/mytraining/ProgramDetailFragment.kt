package com.onebit.mytraining

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.onebit.mytraining.util.FragComm

/**
 * Created by jason on 10/25/17.
 */
class ProgramDetailFragment: Fragment() {
    private lateinit var fragComm: FragComm

    companion object {
        fun newInstance(): ProgramDetailFragment {
            return ProgramDetailFragment()
        }
        val tag = "ProgramDetailFragment"
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_program_detail, container, false)
        fragComm = activity as FragComm
        val gridView = view!!.findViewById<GridView>(R.id.gridview)
        val programDetailsAdapter = ProgramDetailsAdapter(activity,fragComm.getProgram()[1].exercises)
        gridView.adapter = programDetailsAdapter
        return view
    }
}