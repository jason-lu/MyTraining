package com.onebit.mytraining.model

/**
 * Created by jason on 10/25/17.
 */
interface FragComm {
    fun sendProgram(pos: Int)
    fun getProgram(): ArrayList<Program>
}