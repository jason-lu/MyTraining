package com.onebit.mytraining

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by jason on 20/10/17.
 */
class WorkoutFragment: Fragment() {

    companion object {
        fun newInstance(): WorkoutFragment {
            return WorkoutFragment()
        }

        val tag = "WorkoutFragment"
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_workout,container,false)

        return view
    }
}