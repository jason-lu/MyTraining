package com.onebit.mytraining.util

import com.onebit.mytraining.model.Program

/**
 * Created by jason on 10/25/17.
 */
interface FragComm {
    fun sendProgram(pos: Int)
    fun getProgram(): ArrayList<Program>
}